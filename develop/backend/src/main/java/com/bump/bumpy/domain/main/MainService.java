package com.bump.bumpy.domain.main;

import com.bump.bumpy.database.entity.data.DataHAerobic;
import com.bump.bumpy.database.entity.data.DataHInbody;
import com.bump.bumpy.database.entity.data.DataHMeal;
import com.bump.bumpy.database.entity.data.DataHWeight;
import com.bump.bumpy.database.entity.usr.UsrMUsr;
import com.bump.bumpy.database.repository.data.DataHAerobicRepository;
import com.bump.bumpy.database.repository.data.DataHInbodyRepository;
import com.bump.bumpy.database.repository.data.DataHMealRepository;
import com.bump.bumpy.database.repository.data.DataHWeightRepository;
import com.bump.bumpy.database.repository.usr.UsrMUsrRepository;
import com.bump.bumpy.domain.main.dto.AerobicResponseDto;
import com.bump.bumpy.domain.main.dto.ChartRequestDto;
import com.bump.bumpy.domain.main.dto.ChartWeightResponseDto;
import com.bump.bumpy.domain.main.dto.UserInfoResponse;
import com.bump.bumpy.domain.main.dto.WeightResponseDto;
import com.bump.bumpy.domain.screen.dto.SearchMonthRequestDto;
import com.bump.bumpy.domain.screen.meal.dto.DataHMealDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bump.bumpy.util.funtion.FieldValueUtil.dateToString;
import static com.bump.bumpy.util.funtion.FieldValueUtil.getTodayDate;
import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;
import static com.bump.bumpy.util.funtion.FieldValueUtil.isSameDate;
import static com.bump.bumpy.util.funtion.FieldValueUtil.isTodayDate;
import static com.bump.bumpy.util.funtion.FieldValueUtil.isYesterDayDate;

@Service
@RequiredArgsConstructor
public class MainService {

    private final UsrMUsrRepository usrMUsrRepository;
    private final DataHInbodyRepository dataHInbodyRepository;
    private final DataHWeightRepository dataHWeightRepository;
    private final DataHAerobicRepository dataHAerobicRepository;
    private final DataHMealRepository dataHMealRepository;
    private final JPAQueryFactory queryFactory;

    public ResponseEntity<ResultMap> userInfo(String userId) {
        // height, weight, age
        // continuity
        // last active
        // average water
        // Inbody( weight, muscle, fat, bmi, fat rate) )

        UserInfoResponse userInfoResponse = new UserInfoResponse();

        UsrMUsr userData = usrMUsrRepository.findByUserId(userId);

        // get continuity from dataHWeightRepository and dataHCardioRepository
        List<DataHWeight> dataHWeightList = dataHWeightRepository.findByUserIdOrderByStdDateDesc(userId);
        List<DataHAerobic> dataHAerobicList = dataHAerobicRepository.findByUserIdOrderByStdDateDesc(userId);

        // check if neither weight nor aerobic data exists
        if(dataHWeightList.isEmpty() && dataHAerobicList.isEmpty()) {
            userInfoResponse.setContinuity(0);
            userInfoResponse.setLastActive(null);
        } else {
            // get last active from dataHWeightRepository and dataHCardioRepository
            Date lastActive;
            Date lastActiveWeight = dataHWeightList.isEmpty() ? null : dataHWeightList.get(0).getStdDate();
            Date lastActiveCardio = dataHAerobicList.isEmpty() ? null : dataHAerobicList.get(0).getStdDate();

            if(lastActiveWeight == null) {
                lastActive = lastActiveCardio;
            } else if(lastActiveCardio == null) {
                lastActive = lastActiveWeight;
            } else {
                lastActive = lastActiveWeight.compareTo(lastActiveCardio) > 0 ? lastActiveWeight : lastActiveCardio;
            }

            userInfoResponse.setLastActive(lastActive);

            Calendar lastActiveCal = new Calendar.Builder().setInstant(lastActive).build();

            int continuity = 0;

            if(isTodayDate(lastActive) || isYesterDayDate(lastActive)) {
                while(dataHWeightList.stream().anyMatch(dataHWeight -> isSameDate(dataHWeight.getStdDate(), lastActiveCal.getTime())) ||
                        dataHAerobicList.stream().anyMatch(dataHCardio -> isSameDate(dataHCardio.getStdDate(), lastActiveCal.getTime())))
                {
                    continuity++;
                    lastActiveCal.add(Calendar.DATE, -1);
                }
            }

            userInfoResponse.setContinuity(continuity);
        }

        // set Age
        if(userData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("사용자 정보가 없습니다."));
        } else {
            // get age from user.birth
            Date birth = userData.getBirth();
            Calendar nowCal = new Calendar.Builder().setInstant(new Date()).build();
            Calendar birthCal = new Calendar.Builder().setInstant(birth).build();

            int age = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
            if(
                nowCal.get(Calendar.MONTH) < birthCal.get(Calendar.MONTH) ||
                nowCal.get(Calendar.MONTH) == birthCal.get(Calendar.MONTH) && nowCal.get(Calendar.DAY_OF_MONTH) < birthCal.get(Calendar.DAY_OF_MONTH)
            ) { age--; }

            userInfoResponse.setAge(age);
        }

        // set average Water
        // get from 30 days ago to yesterday water data from dataHMealRepository
        Calendar cal = new Calendar.Builder().setInstant(new Date()).build();
        cal.add(Calendar.DATE, -1);
        Date endDate = cal.getTime();
        cal.add(Calendar.DATE, -29);
        Date startDate = cal.getTime();
        List<DataHMeal> mealList = dataHMealRepository.findByUserIdAndStdDateBetween(userId, startDate, endDate);

        Map<Date, BigDecimal> waterMap = new HashMap<>();

        for (DataHMeal meal : mealList) {
            if(waterMap.containsKey(meal.getStdDate())) {
                waterMap.put(meal.getStdDate(), waterMap.get(meal.getStdDate()).add(meal.getWater()));
            } else {
                waterMap.put(meal.getStdDate(), meal.getWater());
            }
        }

        BigDecimal waterSum = BigDecimal.ZERO;

        for (Date date : waterMap.keySet()) {
            BigDecimal water = waterMap.get(date);
            waterSum = waterSum.add(water);
        }

        if(waterSum.compareTo(BigDecimal.ZERO) == 0) {
            userInfoResponse.setAverageWater(BigDecimal.ZERO);
        } else {
            userInfoResponse.setAverageWater(waterSum.divide(BigDecimal.valueOf(waterMap.size()), 2, RoundingMode.HALF_UP));
        }

        // set inbody Data
        DataHInbody inbodyData = dataHInbodyRepository.findFirstByUserIdOrderByStdDateDesc(userId);
        if(inbodyData != null) {
            userInfoResponse.setInbodyData(
                    UserInfoResponse.inbodyData.builder()
                            .weight(inbodyData.getWeight())
                            .muscle(inbodyData.getMuscle())
                            .fat(inbodyData.getFat())
                            .bmi(inbodyData.getBmi())
                            .fatRate(inbodyData.getFatRate())
                            .build()
            );
            userInfoResponse.setHeight(inbodyData.getHeight());
            userInfoResponse.setWeight(inbodyData.getWeight());
        }

        return ResponseEntity.ok(new ResultMap(userInfoResponse));
    }

    public ResponseEntity<ResultMap> grassInfo(SearchMonthRequestDto request) {
        // TODO : 잔디 데이터 출력하기
        /*
        [
            [{date: string , {date: string , flag: false},...] // 1주차, 7개 있어야됨
            [{date: string , {date: string , flag: false},...] // 2주차,
            [{date: string , {date: string , flag: false},...] // 3주차,
            [{date: string , {date: string , flag: false},...] // 4주차,
            [{date: string , {date: string , flag: false},...] // 5주차,
        ]
         */
            return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> mealInfo() {
        List<DataHMeal> mealList = dataHMealRepository.findByUserIdAndStdDateOrderBySeqAsc(getUserId(), getTodayDate());
        List<DataHMealDto> mealDtoList = new ArrayList<>();

        // convert to dto
        for (DataHMeal meal : mealList) {
            DataHMealDto mealDto = null;
            try {
                mealDto = new DataHMealDto(meal);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            mealDtoList.add(mealDto);
        }

        if(mealDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(new ResultMap(mealDtoList));
    }

    public ResponseEntity<ResultMap> activityInfoWeight() {
        // find latest stdDate in dataHWeightRepository by userId
        DataHWeight weightStdDate = dataHWeightRepository.findFirstByUserIdOrderByStdDateDesc(getUserId());
        Date latestWeightStdDate = weightStdDate == null ? null : weightStdDate.getStdDate();

        if(latestWeightStdDate != null) {
            List<DataHWeight> dataHWeightList = dataHWeightRepository.findByStdDateAndUserIdOrderBySeqAsc(latestWeightStdDate, getUserId());

            List<WeightResponseDto> weightResponseDtoList = new ArrayList<>();
            // convert to dto
            for (DataHWeight dataHWeight : dataHWeightList) {
                WeightResponseDto weightResponseDto = new WeightResponseDto(dataHWeight);
                weightResponseDtoList.add(weightResponseDto);
            }

            return ResponseEntity.ok(new ResultMap(weightResponseDtoList));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    public ResponseEntity<ResultMap> activityInfoAerobic() {
        // find latest stdDate in dataHAerobicRepository by userId
        DataHAerobic aerobicStdDate = dataHAerobicRepository.findFirstByUserIdOrderByStdDateDesc(getUserId());
        Date latestAerobicStdDate = aerobicStdDate == null ? null : aerobicStdDate.getStdDate();

        if(latestAerobicStdDate != null) {
            List<DataHAerobic> dataHAerobicList = dataHAerobicRepository.findByStdDateAndUserIdOrderBySeqAsc(latestAerobicStdDate, getUserId());

            List<AerobicResponseDto> aerobicResponseDtoList = new ArrayList<>();
            // convert to dto
            for (DataHAerobic dataHAerobic : dataHAerobicList) {
                AerobicResponseDto aerobicResponseDto = new AerobicResponseDto(dataHAerobic);
                aerobicResponseDtoList.add(aerobicResponseDto);
            }
            return ResponseEntity.ok(new ResultMap(aerobicResponseDtoList));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    /*
        My Best, Month Avg, Reps Avg
        {
            categories : 운동을 한 날짜들
            series : 날짜들에 대응되는 데이터
        }
    */

    // TODO : Weight 차트 데이터 출력 ( 10일치 데이터 )
    // 1. My Best : 전체 기간 중 무게 최고값
    // 2. Chart Average : 10개 데이터의 평균값
    // 3. Reps When Best : My Best 때의 Reps와 Set값

    public ResponseEntity<ResultMap> WeightChart(ChartRequestDto request) {
        ChartWeightResponseDto responseDto = new ChartWeightResponseDto();

        // 1. best weight data & 3. reps and sets when best
        BigDecimal weightStartBestValue = null;
        BigDecimal weightEndBestValue = null;
        DataHWeight weightStartBest = dataHWeightRepository.findFirstByUserIdAndNameOrderByWeightStartDesc(getUserId(), request.getName());
        if(weightStartBest != null) { weightStartBestValue = weightStartBest.getWeightStart(); }
        DataHWeight weightEndBest = dataHWeightRepository.findFirstByUserIdAndNameOrderByWeightEndDesc(getUserId(), request.getName());
        if(weightEndBest != null) { weightEndBestValue = weightEndBest.getWeightEnd(); }

        // find bigger weight & set reps and sets
        BigDecimal bestWeight = null;
        if(weightStartBestValue != null && weightEndBestValue != null) {
            if(weightStartBestValue.compareTo(weightEndBestValue) > 0) {
                bestWeight = weightStartBestValue;
                responseDto.setReps(weightStartBest.getRepsStart());
                responseDto.setSets(weightStartBest.getSetReps());
            } else {
                bestWeight = weightEndBestValue;
                responseDto.setReps(weightEndBest.getRepsEnd());
                responseDto.setSets(weightEndBest.getSetReps());
            }
        } else if(weightStartBestValue != null) {
            bestWeight = weightStartBestValue;
            responseDto.setReps(weightStartBest.getRepsStart());
            responseDto.setSets(weightStartBest.getSetReps());
        } else if(weightEndBestValue != null) {
            bestWeight = weightEndBestValue;
            responseDto.setReps(weightEndBest.getRepsEnd());
            responseDto.setSets(weightEndBest.getSetReps());
        }

        responseDto.setMyBest(bestWeight);

        // 2. Average from latest 10 data
        List<DataHWeight> top10Weight = dataHWeightRepository.findTop10ByUserIdAndNameOrderByStdDateDesc(getUserId(), request.getName());

        BigDecimal averageWeight = null;
        if(!top10Weight.isEmpty()) {
            BigDecimal sum = new BigDecimal(0);
            for (DataHWeight dataHWeight : top10Weight) {
                // find bigger weight
                // sum += bigger weight
                // null check

                BigDecimal biggerWeight = findBiggerWeight(dataHWeight);
                if(biggerWeight != null) {
                    sum = sum.add(biggerWeight);
                }
            }
            // 평균값 소수점 2자리 반올림
            averageWeight = sum.divide(new BigDecimal(top10Weight.size()), 2, RoundingMode.HALF_UP);
        }

        responseDto.setMonthAverage(averageWeight);

        // Chart Data
        // 가장 최근 운동일의 데이터들을 기준으로 해당 운동의 최근 10개의 데이터를 가져온다.

        responseDto.setXAxis(new ArrayList<>());

        // get 10 datas from dataHWeightRepository by userId and name
        List<DataHWeight> dataHWeightListByName = dataHWeightRepository.findTop10ByUserIdAndNameOrderByStdDateDesc(getUserId(), request.getName());

        // 날짜 과거 -> 현재 순으로 정렬
        Collections.reverse(dataHWeightListByName);

        List<BigDecimal> weightList = new ArrayList<>();
        for (DataHWeight dataHWeight : dataHWeightListByName) {
            BigDecimal biggerWeight = findBiggerWeight(dataHWeight);
            if(biggerWeight != null) {
                responseDto.getXAxis().add(dateToString(dataHWeight.getStdDate()));
                weightList.add(biggerWeight);
            }
        }
        responseDto.setSeries(List.of(Map.of(request.getName(), weightList)));

        return ResponseEntity.ok(new ResultMap(responseDto));
    }
    
    // TODO : Aerobic 차트 데이터 출력 ( 10일치 데이터 )
    // 1. Best Kcal : 전체 기간 중 최대 Kcal 값
    // 2. Best Time : Best Kcal 일때의 시간값
    // 3. Month Averager : Incline / Speed (최근 10개에 대한 평균)

    public ResponseEntity<ResultMap> AerobicChart(ChartRequestDto request) {
        // find latest stdDate in dataHAerobicRepository by userId
        DataHAerobic aerobicStdDate = dataHAerobicRepository.findFirstByUserIdOrderByStdDateDesc(getUserId());
        Date latestAerobicStdDate = aerobicStdDate == null ? null : aerobicStdDate.getStdDate();

        if(latestAerobicStdDate != null) {

        }

        return ResponseEntity.ok(new ResultMap());
    }

    // find bigger weight from dataHWeight
    private BigDecimal findBiggerWeight(DataHWeight dataHWeight) {
        BigDecimal weightStart = dataHWeight.getWeightStart();
        BigDecimal weightEnd = dataHWeight.getWeightEnd();

        if (weightStart != null && weightEnd != null) {
            if (weightStart.compareTo(weightEnd) > 0) {
                return weightStart;
            } else {
                return weightEnd;
            }
        } else if (weightStart != null) {
            return weightStart;
        } else if (weightEnd != null) {
            return weightEnd;
        } else {
            return null;
        }
    }

}
