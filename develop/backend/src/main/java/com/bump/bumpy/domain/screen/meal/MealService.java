package com.bump.bumpy.domain.screen.meal;

import com.bump.bumpy.database.entity.data.DataHMeal;
import com.bump.bumpy.database.repository.data.DataHMealRepository;
import com.bump.bumpy.domain.common.CommonService;
import com.bump.bumpy.domain.screen.dto.SearchDateRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchMonthRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.meal.dto.DataHMealDto;
import com.bump.bumpy.domain.screen.meal.dto.MealResponse;
import com.bump.bumpy.util.dto.PictureDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.dto.youtube.ResponseKeywordSearchDto;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import com.bump.bumpy.util.funtion.YoutubeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getFirstDateOfPrevMonth;
import static com.bump.bumpy.util.funtion.FieldValueUtil.getLastDateOfNextMonth;

@Service
@RequiredArgsConstructor
public class MealService {

    private final DataHMealRepository dataHMealRepository;
    private final CommonService commonService;
    private final YoutubeService youtubeService;

    public ResponseEntity<ResultMap> calendar(SearchMonthRequestDto request) {
        // prev month first date, next month last date
        Date firstDateOfPrevMonth = getFirstDateOfPrevMonth(request.getStdDate());
        Date lastDateOfNextMonth = getLastDateOfNextMonth(request.getStdDate());

        // find data from first date to last date
        List<DataHMeal> dataHMealList = dataHMealRepository.findByStdDateBetweenAndUserIdOrderByStdDateAscSeqAsc(firstDateOfPrevMonth, lastDateOfNextMonth, request.getUserId());

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

        List<MealResponse> mealActivityResponseDtoList = new ArrayList<>();

        for(DataHMeal meal : dataHMealList) {
            try {
                List<Map<String, String>> imageList = commonService.getFileBase64Internal(meal.getPicture());
                mealActivityResponseDtoList.add(new MealResponse(meal, imageList));
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
                List<Map<String, String>> imageList = commonService.getFileBase64Internal(dataHMeal.getPicture());
                return ResponseEntity.ok(new ResultMap(new MealResponse(dataHMeal, imageList)));
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

        if(dataHMealRepository.findByStdDateAndUserIdAndName(request.getStdDate(), userId, request.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 등록된 데이터입니다."));
        }

        // get max seq from data
        DataHMeal maxSeqData = dataHMealRepository.findFirstByStdDateAndUserIdOrderBySeqDesc(request.getStdDate(), userId);

        int seq = 1;
        if(maxSeqData != null) {
            seq = maxSeqData.getSeq() + 1;
        }

        List<String> uuidList = new ArrayList<>();

        // upload files
        for (PictureDto pictureDto : request.getPicture()) {
            String uuid = commonService.uploadBase64ImageInternal(pictureDto, userId);
            uuidList.add(uuid);
        }

        DataHMeal dataHMeal = null;
        try {
            dataHMeal = request.toEntity(seq, uuidList);
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
                List<String> uuidList = new ArrayList<>();

                // delete files
                List<String> deleteFileList = dataHMeal.getPicture();

                for(String uuid : deleteFileList) {
                    commonService.deleteFileInternal(uuid);
                }

                // upload files
                for (PictureDto pictureDto : request.getPicture()) {
                    String uuid = commonService.uploadBase64ImageInternal(pictureDto, userId);
                    uuidList.add(uuid);
                }

                dataHMeal = request.updateEntity(dataHMeal, uuidList);
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
            // delete files
            List<String> deleteFileList = dataHMeal.getPicture();

            for(String uuid : deleteFileList) {
                commonService.deleteFileInternal(uuid);
            }

            dataHMealRepository.delete(dataHMeal);

            return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
        }
    }

    public ResponseEntity<ResultMap> youtubeSearch() {
        List<String> list = List.of("건강하게 먹기", "살고싶으면 챙겨야할 영양소", "식이요법", "다이어트 식단");

        Random random = new Random();
        int i = random.nextInt(list.size() - 1);

        ResponseKeywordSearchDto result = youtubeService.searchListKeyword(list.get(i));

        return ResponseEntity.ok(new ResultMap(result.getDetailList()));
    }
}
