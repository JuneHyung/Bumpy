package com.bump.bumpy.domain.screen.weight;

import com.bump.bumpy.database.entity.data.DataHWeight;
import com.bump.bumpy.database.repository.data.DataHWeightRepository;
import com.bump.bumpy.domain.common.CommonService;
import com.bump.bumpy.domain.screen.dto.SearchDateRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchMonthRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.weight.dto.DataHWeightDto;
import com.bump.bumpy.domain.screen.weight.dto.WeightActivityResponseDto;
import com.bump.bumpy.domain.screen.weight.projection.DataHWeightInfo;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.bump.bumpy.util.funtion.FieldValueUtil.isTodayDate;
import static com.bump.bumpy.util.funtion.FieldValueUtil.setZeroTime;

@Service
@RequiredArgsConstructor
public class WeightService {

    private final DataHWeightRepository dataHWeightRepository;
    private final CommonService commonService;

    public ResponseEntity<ResultMap> favorite(String userId) {
        List<DataHWeightInfo> nameList = dataHWeightRepository.findByUserIdOrderByNameAsc(userId);

        if(nameList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        // Set to List
        Set<String> nameSet = new HashSet<>();
        for(DataHWeightInfo dataHWeightInfo : nameList) {
            nameSet.add(dataHWeightInfo.getName());
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
        List<DataHWeight> dataHWeightList = dataHWeightRepository.findByStdDateBetweenAndUserIdOrderByStdDateAscSeqAsc(firstDateOfMonth, lastDateOfMonth, request.getUserId());

        if(dataHWeightList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        // make calendar map data
        List<Map<String, String>> calendarList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (DataHWeight weight : dataHWeightList) {
            Map<String, String> calendarData = new HashMap<>();
            String date = simpleDateFormat.format(weight.getStdDate());
            calendarData.put("title", weight.getName());
            calendarData.put("date", date);
            calendarList.add(calendarData);
        }

        return ResponseEntity.ok(new ResultMap(calendarList));
    }

    public ResponseEntity<ResultMap> activity(SearchDateRequestDto request) {
        List<DataHWeight> dataHWeightList = dataHWeightRepository.findByStdDateAndUserIdOrderBySeqAsc(request.getStdDate(), request.getUserId());

        if(dataHWeightList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<WeightActivityResponseDto> weightActivityResponseDtoList = new ArrayList<>();

        for(DataHWeight weightData : dataHWeightList) {
            weightActivityResponseDtoList.add(new WeightActivityResponseDto(weightData));
        }

        return ResponseEntity.ok(new ResultMap(weightActivityResponseDtoList));
    }

    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        DataHWeight dataHWeight = dataHWeightRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null);
        if(dataHWeight == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        } else {
            return ResponseEntity.ok(new ResultMap(dataHWeight));
        }
    }

//    @Transactional(rollbackFor = Exception.class)
//    public ResponseEntity<ResultMap> insert(DataHWeightDto request, String userId) {
//        if(!isTodayDate(request.getStdDate())) {
//            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
//        }
//
//        if(dataHWeightRepository.findByStdDateAndUserIdAndName(request.getStdDate(), userId, request.getName()).isPresent()) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 데이터가 존재합니다."));
//        }
//
//        // get max seq
//        DataHWeight maxSeqData = dataHWeightRepository.findFirstByStdDateAndUserIdOrderBySeqDesc(request.getStdDate(), userId);
//
//        int maxSeq = 1;
//        if(maxSeqData != null) {
//            maxSeq = maxSeqData.getSeq() + 1;
//        }
//
//        DataHWeight dataHWeight = request.toEntity(maxSeq);
//        dataHWeight.setUserId(userId);
//
//        dataHWeightRepository.save(dataHWeight);
//
//        return ResponseEntity.ok(new ResultMap("message", "저장되었습니다."));
//    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHWeightDto request, MultipartFile[] files, String userId) {
        if(!isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        if(dataHWeightRepository.findByStdDateAndUserIdAndName(request.getStdDate(), userId, request.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 데이터가 존재합니다."));
        }

        List<String> uuidList = new ArrayList<>();

        // upload files
        for (MultipartFile file : files) {
            String uuid = commonService.uploadFileInternal(file, userId);
            uuidList.add(uuid);
        }

        // get max seq
        DataHWeight maxSeqData = dataHWeightRepository.findFirstByStdDateAndUserIdOrderBySeqDesc(request.getStdDate(), userId);

        int maxSeq = 1;
        if(maxSeqData != null) {
            maxSeq = maxSeqData.getSeq() + 1;
        }

        DataHWeight dataHWeight = request.toEntity(maxSeq, uuidList);
        dataHWeight.setUserId(userId);

        dataHWeightRepository.save(dataHWeight);

        return ResponseEntity.ok(new ResultMap("message", "저장되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> update(DataHWeightDto request, String userId) {
        if(!isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        DataHWeight dataHWeight = dataHWeightRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).orElse(null);

        if(dataHWeight == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        } else {
            dataHWeight = request.updateEntity(dataHWeight);

            dataHWeightRepository.save(dataHWeight);

            return ResponseEntity.ok(new ResultMap("message", "수정되었습니다."));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        if(!isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        DataHWeight dataHWeight = dataHWeightRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq())
                .orElse(null);

        if(dataHWeight == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        } else {
            dataHWeightRepository.delete(dataHWeight);

            return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
        }
    }
}
