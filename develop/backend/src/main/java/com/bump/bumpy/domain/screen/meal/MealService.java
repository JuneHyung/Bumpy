package com.bump.bumpy.domain.screen.meal;

import com.bump.bumpy.database.entity.data.DataHMeal;
import com.bump.bumpy.database.repository.data.DataHMealRepository;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.meal.dto.DataHMealDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import lombok.RequiredArgsConstructor;
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
            dataHMealList.forEach(dataHMeal -> {
                list.add(new DataHMealDto(dataHMeal));
            });
        } else {
            DataHMeal dataHMeal = dataHMealRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null);
            if(dataHMeal != null) {
                list.add(new DataHMealDto(dataHMeal));
            }
        }
        return ResponseEntity.ok(new ResultMap(list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHMealDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        if(dataHMealRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 데이터입니다.");
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

        DataHMeal dataHMeal = dataHMealRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));

        dataHMeal = request.updateEntity(dataHMeal);

        dataHMealRepository.save(dataHMeal);

        return ResponseEntity.ok(new ResultMap("message", "수정되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }
        DataHMeal dataHMeal = dataHMealRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));
        dataHMealRepository.delete(dataHMeal);

        return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
    }
}
