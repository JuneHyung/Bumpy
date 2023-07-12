package com.bump.bumpy.domain.screen.meal;

import com.bump.bumpy.database.entity.data.DataHMeal;
import com.bump.bumpy.database.repository.data.DataHMealRepository;
import com.bump.bumpy.domain.screen.dto.SearchDateRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchMonthRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.meal.dto.DataHMealDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bump.bumpy.util.funtion.FieldValueUtil.setZeroTime;

@Service
@RequiredArgsConstructor
public class MealService {

    private final DataHMealRepository dataHMealRepository;

    public ResponseEntity<ResultMap> calendar(SearchMonthRequestDto request) {
        Calendar firstDate = Calendar.getInstance();
        firstDate.setTime(request.getStdDate());
        firstDate.set(Calendar.DAY_OF_MONTH, 1);
        firstDate = setZeroTime(firstDate);

        Date firstDateOfMonth = firstDate.getTime();

        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(request.getStdDate());
        lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        lastDate = setZeroTime(lastDate);

        Date lastDateOfMonth = lastDate.getTime();

        // find data from first date to last date
        List<DataHMeal> dataHMealList = dataHMealRepository.findByStdDateBetweenAndUserIdOrderByStdDateAscSeqAsc(firstDateOfMonth, lastDateOfMonth, request.getUserId());

        if(dataHMealList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        // make calendar map data
        List<Map<String, String>> calendarList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (DataHMeal meal : dataHMealList) {
            Map<String, String> calendarData = new HashMap<>();
            String date = simpleDateFormat.format(meal.getStdDate());
            calendarData.put("title", meal.getName());
            calendarData.put("date", date);
            calendarList.add(calendarData);
        }

        return ResponseEntity.ok(new ResultMap(calendarList));
    }

    public ResponseEntity<ResultMap> activity(SearchDateRequestDto request) {
        List<DataHMeal> dataHMealList = dataHMealRepository.findByUserIdAndStdDateOrderBySeqAsc(request.getUserId(), request.getStdDate());

        if(dataHMealList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<DataHMealDto> mealActivityResponseDtoList = new ArrayList<>();

        for(DataHMeal meal : dataHMealList) {
            try {
                mealActivityResponseDtoList.add(new DataHMealDto(meal));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.ok(new ResultMap(mealActivityResponseDtoList));
    }

    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        DataHMeal dataHMeal = dataHMealRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null);
        if(dataHMeal != null) {
            try {
                return ResponseEntity.ok(new ResultMap(new DataHMealDto(dataHMeal)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHMealDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        if(dataHMealRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 등록된 데이터입니다."));
        }

        DataHMeal dataHMeal = null;
        try {
            dataHMeal = request.toEntity();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        dataHMeal.setUserId(userId);

        dataHMealRepository.save(dataHMeal);

        return ResponseEntity.ok(new ResultMap("message", "저장되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> update(DataHMealDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        DataHMeal dataHMeal = dataHMealRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).orElse(null);

        if(dataHMeal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        } else {
            try {
                dataHMeal = request.updateEntity(dataHMeal);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            dataHMealRepository.save(dataHMeal);

            return ResponseEntity.ok(new ResultMap("message", "수정되었습니다."));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        DataHMeal dataHMeal = dataHMealRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq())
                .orElse(null);

        if(dataHMeal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        } else {
            dataHMealRepository.delete(dataHMeal);

            return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
        }
    }
}
