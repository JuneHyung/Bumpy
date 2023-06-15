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
import com.bump.bumpy.domain.main.dto.UserInfoResponse;
import com.bump.bumpy.domain.main.dto.WeightResponseDto;
import com.bump.bumpy.domain.screen.meal.dto.DataHMealDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ResponseEntity<ResultMap> chart() { return ResponseEntity.ok(new ResultMap()); }
}
