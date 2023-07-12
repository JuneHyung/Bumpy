package com.bump.bumpy.domain.screen.aerobic;

import com.bump.bumpy.database.entity.data.DataHAerobic;
import com.bump.bumpy.database.repository.data.DataHAerobicRepository;
import com.bump.bumpy.domain.screen.aerobic.dto.AerobicActivityResponseDto;
import com.bump.bumpy.domain.screen.aerobic.dto.DataHAerobicDto;
import com.bump.bumpy.domain.screen.dto.SearchDateRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchMonthRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
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
public class AerobicService {

    private final DataHAerobicRepository aerobicRepository;

    public ResponseEntity<ResultMap> calendar(SearchMonthRequestDto request) {
        /*
            달력에 나갈 데이터 DTO 형태
            { data : [ {date: name}, {date: name}, {date: name} ] }
         */

        // find first date and last date based on request.getStdDate()
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
        List<DataHAerobic> DataHAerobicList = aerobicRepository.findByStdDateBetweenAndUserIdOrderByStdDateAscSeqAsc(firstDateOfMonth, lastDateOfMonth, request.getUserId());

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
            return ResponseEntity.ok(new ResultMap(dataHAerobic));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHAerobicDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다."); // 400 Bad Request
        }

        if(aerobicRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 등록된 데이터입니다."));
        }

        DataHAerobic dataHAerobic = request.toEntity();
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
            aerobicRepository.delete(dataHAerobic);
            return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
        }
    }
}
