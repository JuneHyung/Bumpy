package com.bump.bumpy.domain.main;

import com.bump.bumpy.database.entity.data.DataHCardio;
import com.bump.bumpy.database.entity.data.DataHInbody;
import com.bump.bumpy.database.entity.data.DataHMeal;
import com.bump.bumpy.database.entity.data.DataHWeight;
import com.bump.bumpy.database.entity.usr.UsrMUsr;
import com.bump.bumpy.database.repository.data.DataHCardioRepository;
import com.bump.bumpy.database.repository.data.DataHInbodyRepository;
import com.bump.bumpy.database.repository.data.DataHMealRepository;
import com.bump.bumpy.database.repository.data.DataHWeightRepository;
import com.bump.bumpy.database.repository.usr.UsrMUsrRepository;
import com.bump.bumpy.domain.main.dto.UserInfoResponse;
import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainService {

    private final UsrMUsrRepository usrMUsrRepository;
    private final DataHInbodyRepository dataHInbodyRepository;
    private final DataHWeightRepository dataHWeightRepository;
    private final DataHCardioRepository dataHCardioRepository;
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
        List<DataHCardio> dataHCardioList = dataHCardioRepository.findByUserIdOrderByStdDateDesc(userId);

        // check if neither weight nor cardio data exists
        if(dataHWeightList.isEmpty() && dataHCardioList.isEmpty()) {
            userInfoResponse.setContinuity(0);
            userInfoResponse.setLastActive(null);
        } else {
            // get last active from dataHWeightRepository and dataHCardioRepository
            Date lastActive;
            Date lastActiveWeight = dataHWeightList.isEmpty() ? null : dataHWeightList.get(0).getStdDate();
            Date lastActiveCardio = dataHCardioList.isEmpty() ? null : dataHCardioList.get(0).getStdDate();

            if(lastActiveWeight == null) {
                lastActive = lastActiveCardio;
            } else if(lastActiveCardio == null) {
                lastActive = lastActiveWeight;
            } else {
                lastActive = lastActiveWeight.compareTo(lastActiveCardio) > 0 ? lastActiveWeight : lastActiveCardio;
            }

            userInfoResponse.setLastActive(lastActive);

            // get continuity
            Calendar nowCal = new Calendar.Builder().setInstant(new Date()).build();
            Calendar yesterdayCal = new Calendar.Builder().setInstant(new Date()).build();
            yesterdayCal.add(Calendar.DATE, -1);
            Calendar lastActiveCal = new Calendar.Builder().setInstant(lastActive).build();

            int continuity = 0;

            if(nowCal.compareTo(lastActiveCal) == 0 || yesterdayCal.compareTo(lastActiveCal) == 0) {
                while(nowCal.compareTo(lastActiveCal) >= 0) {
                    if(dataHWeightList.stream().anyMatch(dataHWeight -> dataHWeight.getStdDate().equals(lastActiveCal.getTime())) ||
                            dataHCardioList.stream().anyMatch(dataHCardio -> dataHCardio.getStdDate().equals(lastActiveCal.getTime()))) {
                        continuity++;
                    } else {
                        break;
                    }
                    lastActiveCal.add(Calendar.DATE, -1);
                }
            }

            userInfoResponse.setContinuity(continuity);
        }

        // set Age
        if(userData == null) {
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
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

        // get last active from dataHCardioRepository and dataHWeightRepository
        Date lastActive;
        DataHCardio dataHCardio = dataHCardioRepository.findFirstByUserIdOrderByStdDateDesc(userId);
        DataHWeight dataHWeight = dataHWeightRepository.findFirstByUserIdOrderByStdDateDesc(userId);
        Date lastActiveCardio = dataHCardio != null ? dataHCardio.getStdDate() : null;
        Date lastActiveWeight = dataHWeight != null ? dataHWeight.getStdDate() : null;
        // check if neither cardio nor weight data exists
        if(lastActiveCardio == null && lastActiveWeight == null) {
            lastActive = null;
        } else if(lastActiveCardio == null) {
            lastActive = lastActiveWeight;
        } else if(lastActiveWeight == null) {
            lastActive = lastActiveCardio;
        } else if(lastActiveCardio.compareTo(lastActiveWeight) > 0) {
            lastActive = lastActiveCardio;
        } else {
            lastActive = lastActiveWeight;
        }
        userInfoResponse.setLastActive(lastActive);

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

        return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> mealInfo() {
        return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> activityInfo() {
        return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> chart() { return ResponseEntity.ok(new ResultMap()); }
}
