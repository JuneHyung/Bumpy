package com.bump.bumpy.domain.screen.meal;

import com.bump.bumpy.database.entity.data.DataHMeal;
import com.bump.bumpy.database.repository.data.DataHMealRepository;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.meal.dto.DataHMealDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final DataHMealRepository dataHMealRepository;

    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        List<DataHMealDto> list = new ArrayList<>();
        if(request.getSeq() == null) {
            List<DataHMeal> dataHMealList = dataHMealRepository.findByStdDateAndUserIdOrderBySeqAsc(request.getStdDate(), request.getUserId());
            dataHMealList.forEach(dataHMeal -> list.add(new DataHMealDto(dataHMeal)));
            if(list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResultMap("message", "데이터가 없습니다."));
            } else {
                return ResponseEntity.ok(new ResultMap(list));
            }
        } else {
            DataHMeal dataHMeal = dataHMealRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null);
            if(dataHMeal != null) {
                return ResponseEntity.ok(new ResultMap(new DataHMealDto(dataHMeal)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
            }
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

        DataHMeal dataHMeal = request.toEntity();
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
            dataHMeal = request.updateEntity(dataHMeal);

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
