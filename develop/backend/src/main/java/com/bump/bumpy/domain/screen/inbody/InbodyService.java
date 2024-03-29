package com.bump.bumpy.domain.screen.inbody;

import com.bump.bumpy.database.entity.data.DataHInbody;
import com.bump.bumpy.database.repository.data.DataHInbodyRepository;
import com.bump.bumpy.domain.common.CommonService;
import com.bump.bumpy.domain.screen.dto.SearchDateRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchMonthRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.inbody.dto.DataHInbodyDto;
import com.bump.bumpy.domain.screen.inbody.dto.InbodyResponse;
import com.bump.bumpy.domain.screen.inbody.dto.SearchInbodyDto;
import com.bump.bumpy.util.dto.PictureDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.dto.youtube.ResponseKeywordSearchDto;
import com.bump.bumpy.util.funtion.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class InbodyService {

    private final DataHInbodyRepository dataHInbodyRepository;
    private final CommonService commonService;
    private final YoutubeService youtubeService;

    public ResponseEntity<ResultMap> calendar(SearchMonthRequestDto request) {
        // prev month first date, next month last date
        Date firstDateOfPrevMonth = getFirstDateOfPrevMonth(request.getStdDate());
        Date lastDateOfNextMonth = getLastDateOfNextMonth(request.getStdDate());

        // find data from first date to last date
        List<DataHInbody> dataHInbodyList = dataHInbodyRepository.findByStdDateBetweenAndUserId(firstDateOfPrevMonth, lastDateOfNextMonth, request.getUserId());

        if(dataHInbodyList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        // make calendar map data
        List<Map<String, String>> calendarList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (DataHInbody inbody : dataHInbodyList) {
            Map<String, String> calendarData = new HashMap<>();
            String date = simpleDateFormat.format(inbody.getStdDate());
            calendarData.put("title", date + " Inbody");
            calendarData.put("date", date);
            calendarList.add(calendarData);
        }

        return ResponseEntity.ok(new ResultMap(calendarList));
    }

    public ResponseEntity<ResultMap> activity(SearchDateRequestDto request) {
        // find data from first date to last date
        DataHInbody inbody = dataHInbodyRepository.findByStdDateAndUserId(request.getStdDate(), request.getUserId()).orElse(null);

        if(inbody == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(new ResultMap(List.of(inbody)));
    }

    public ResponseEntity<ResultMap> search(SearchInbodyDto request) {
        DataHInbody dataHInbody = dataHInbodyRepository.findByStdDateAndUserId(request.getStdDate(), request.getUserId()).orElse(null);
        if(dataHInbody == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            List<Map<String, String>> imageList = commonService.getFileBase64Internal(dataHInbody.getPicture());
            return ResponseEntity.ok(new ResultMap(new InbodyResponse(dataHInbody, imageList)));
        }
    }

//    @Transactional(rollbackFor = Exception.class)
//    public ResponseEntity<ResultMap> insert(DataHInbodyDto request, String userId) {
//        if(dataHInbodyRepository.findByStdDateAndUserId(request.getStdDate(), userId).isPresent()) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 등록된 데이터입니다."));
//        }
//
//        // if fatRate is null, calculate it
//        if(request.getFatRate() == null) {
//            request.setFatRate(request.getFat().multiply(new BigDecimal(100)).divide(request.getWeight(), RoundingMode.HALF_UP));
//        }
//
//        // if bmi is null, calculate it
//        if(request.getBmi() == null) {
//            request.setBmi(request.getWeight().divide(request.getHeight().divide(new BigDecimal(100), RoundingMode.HALF_UP).pow(2), RoundingMode.HALF_UP));
//        }
//
//        DataHInbody dataHInbody = request.toEntity();
//        dataHInbody.setUserId(userId);
//
//        dataHInbodyRepository.save(dataHInbody);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultMap("message", "저장되었습니다."));
//    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHInbodyDto request, String userId) {
        if(dataHInbodyRepository.findByStdDateAndUserId(request.getStdDate(), userId).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 등록된 데이터입니다."));
        }

        // if fatRate is null, calculate it
        if(request.getFatRate() == null) {
            request.setFatRate(request.getFat().multiply(new BigDecimal(100)).divide(request.getWeight(), RoundingMode.HALF_UP));
        }

        // if bmi is null, calculate it
        if(request.getBmi() == null) {
            request.setBmi(request.getWeight().divide(request.getHeight().divide(new BigDecimal(100), RoundingMode.HALF_UP).pow(2), RoundingMode.HALF_UP));
        }

        List<String> uuidList = new ArrayList<>();

        // upload files
        for (PictureDto pictureDto : request.getPicture()) {
            String uuid = commonService.uploadBase64ImageInternal(pictureDto, userId);
            uuidList.add(uuid);
        }

        DataHInbody dataHInbody = request.toEntity(uuidList);
        dataHInbody.setUserId(userId);

        dataHInbodyRepository.save(dataHInbody);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultMap("message", "저장되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> update(DataHInbodyDto request, String userId) {
        DataHInbody dataHInbody = dataHInbodyRepository.findByStdDateAndUserId(request.getStdDate(), userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));

        List<String> uuidList = new ArrayList<>();

        // delete files
        List<String> deleteFileList = dataHInbody.getPicture();

        for(String uuid : deleteFileList) {
            commonService.deleteFileInternal(uuid);
        }

        // upload files
        for (PictureDto pictureDto : request.getPicture()) {
            String uuid = commonService.uploadBase64ImageInternal(pictureDto, userId);
            uuidList.add(uuid);
        }

        dataHInbody = request.updateEntity(dataHInbody, uuidList);

        dataHInbodyRepository.save(dataHInbody);
        return ResponseEntity.ok(new ResultMap("message", "수정되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        DataHInbody dataHInbody = dataHInbodyRepository.findByStdDateAndUserId(request.getStdDate(), request.getUserId()).orElse(null);
        if(dataHInbody == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "해당 데이터가 없습니다."));
        }
        // delete files
        List<String> deleteFileList = dataHInbody.getPicture();

        for(String uuid : deleteFileList) {
            commonService.deleteFileInternal(uuid);
        }

        dataHInbodyRepository.delete(dataHInbody);
        return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
    }

    public ResponseEntity<ResultMap> youtubeSearch() {
        List<String> list = List.of("인바디 해석", "인바디", "인바디 챙겨보기");

        Random random = new Random();
        int i = random.nextInt(list.size() - 1);

        ResponseKeywordSearchDto result = youtubeService.searchListKeyword(list.get(i));

        return ResponseEntity.ok(new ResultMap(result.getDetailList()));
    }
}
