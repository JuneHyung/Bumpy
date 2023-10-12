package com.bump.bumpy.domain.screen.aerobic;

import com.bump.bumpy.database.entity.data.DataHAerobic;
import com.bump.bumpy.database.repository.data.DataHAerobicRepository;
import com.bump.bumpy.domain.common.CommonService;
import com.bump.bumpy.domain.screen.aerobic.dto.AerobicActivityResponseDto;
import com.bump.bumpy.domain.screen.aerobic.dto.AerobicResponse;
import com.bump.bumpy.domain.screen.aerobic.dto.DataHAerobicDto;
import com.bump.bumpy.domain.screen.aerobic.projection.DataHAerobicInfo;
import com.bump.bumpy.domain.screen.dto.SearchDateRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchMonthRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchYoutubeDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.dto.youtube.ResponseKeywordSearchDto;
import com.bump.bumpy.util.dto.youtube.SearchListKeywordDto;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import com.bump.bumpy.util.funtion.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getFirstDateOfPrevMonth;
import static com.bump.bumpy.util.funtion.FieldValueUtil.getLastDateOfNextMonth;

@Service
@RequiredArgsConstructor
public class AerobicService {

    private final DataHAerobicRepository aerobicRepository;
    private final CommonService commonService;
    private final YoutubeService youtubeService;

    public ResponseEntity<ResultMap> favorite(String userId) {
        List<DataHAerobicInfo> nameList = aerobicRepository.findByUserIdOrderByNameAsc(userId);

        if(nameList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        // Set to List
        Set<String> nameSet = new HashSet<>();
        for(DataHAerobicInfo dataHAerobicInfo : nameList) {
            nameSet.add(dataHAerobicInfo.getName());
        }

        // separate by languages that kor or eng
        List<String> koreanList = new ArrayList<>();
        List<String> englishList = new ArrayList<>();

        for(String name : nameSet) {
            if(FieldValueUtil.isStartWithKorean(name)) {
                koreanList.add(name);
            } else {
                englishList.add(name);
            }
        }

        // sort by string
        Collections.sort(koreanList);
        Collections.sort(englishList);

        return ResponseEntity.ok(
            new ResultMap(
                List.of(
                        Map.of("title", "korean", "list", koreanList),
                        Map.of("title", "english", "list", englishList)
                )
            )
        );
    }

    public ResponseEntity<ResultMap> calendar(SearchMonthRequestDto request) {
        // prev month first date, next month last date
        Date firstDateOfPrevMonth = getFirstDateOfPrevMonth(request.getStdDate());
        Date lastDateOfNextMonth = getLastDateOfNextMonth(request.getStdDate());

        // find data from first date to last date
        List<DataHAerobic> DataHAerobicList = aerobicRepository.findByStdDateBetweenAndUserIdOrderByStdDateAscSeqAsc(firstDateOfPrevMonth, lastDateOfNextMonth, request.getUserId());

        if(DataHAerobicList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        // make calendar map data
        List<Map<String, String>> calendarList = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (DataHAerobic aerobic : DataHAerobicList) {
            // aerobic.getStdDate() to yyyy-MM-dd
            Map<String, String> calendarData = new HashMap<>();
            String date = simpleDateFormat.format(aerobic.getStdDate());
            calendarData.put("title", aerobic.getName());
            calendarData.put("date", date);
            calendarList.add(calendarData);
        }

        return ResponseEntity.ok(new ResultMap(calendarList));
    }

    public ResponseEntity<ResultMap> activity(SearchDateRequestDto request) {
        List<DataHAerobic> DataHAerobicList = aerobicRepository.findByStdDateAndUserIdOrderBySeqAsc(request.getStdDate(), request.getUserId());

        if(DataHAerobicList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<AerobicActivityResponseDto> aerobicActivityResponseDtoList = new ArrayList<>();

        for(DataHAerobic aerobicData : DataHAerobicList) {
            aerobicActivityResponseDtoList.add(new AerobicActivityResponseDto(aerobicData));
        }

        return ResponseEntity.ok(new ResultMap(aerobicActivityResponseDtoList));
    }

    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        DataHAerobic dataHAerobic = aerobicRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null);
        if(dataHAerobic != null) {
//            List<Map<String, String>> imageList = commonService.getFileBase64Internal(dataHAerobic.getPicture());
            return ResponseEntity.ok(new ResultMap(new AerobicResponse(dataHAerobic)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHAerobicDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다."); // 400 Bad Request
        }

        if(aerobicRepository.findByStdDateAndUserIdAndName(request.getStdDate(), userId, request.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 등록된 데이터입니다."));
        }

        // get seq from db
        DataHAerobic maxSeqData = aerobicRepository.findFirstByStdDateAndUserIdOrderBySeqDesc(request.getStdDate(), userId);

        int seq = 1;
        if(maxSeqData != null) {
            seq = maxSeqData.getSeq() + 1;
        }

//        List<String> uuidList = new ArrayList<>();
//
//        // upload files
//        for (PictureDto pictureDto : request.getPicture()) {
//            String uuid = commonService.uploadBase64ImageInternal(pictureDto, userId);
//            uuidList.add(uuid);
//        }

        DataHAerobic dataHAerobic = request.toEntity(seq);
        dataHAerobic.setUserId(userId);

        aerobicRepository.save(dataHAerobic);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultMap("message", "저장되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> update(@NotNull DataHAerobicDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다."); // 400 Bad Request
        }

        DataHAerobic dataHAerobic = aerobicRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).orElse(null);

        if(dataHAerobic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        } else {
            // delete files
//            List<String> deleteFileList = dataHAerobic.getPicture();
//
//            for(String uuid : deleteFileList) {
//                commonService.deleteFileInternal(uuid);
//            }
//
//            List<String> uuidList = new ArrayList<>();
//
//            // upload files
//            for (PictureDto pictureDto : request.getPicture()) {
//                String uuid = commonService.uploadBase64ImageInternal(pictureDto, userId);
//                uuidList.add(uuid);
//            }

            dataHAerobic = request.updateEntity(dataHAerobic);

            aerobicRepository.save(dataHAerobic);

            return ResponseEntity.ok(new ResultMap("message", "수정되었습니다."));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }
        DataHAerobic dataHAerobic = aerobicRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq())
                .orElse(null);

        if(dataHAerobic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        } else {
            // delete files
//            List<String> deleteFileList = dataHAerobic.getPicture();
//
//            for(String uuid : deleteFileList) {
//                commonService.deleteFileInternal(uuid);
//            }

            aerobicRepository.delete(dataHAerobic);
            return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
        }
    }

    public ResponseEntity<ResultMap> youtubeSearch(SearchYoutubeDto request) {
        ResponseKeywordSearchDto result = youtubeService.searchListKeyword(request.getKeyword());

        return ResponseEntity.ok(new ResultMap(result.getDetailList()));
    }
}
