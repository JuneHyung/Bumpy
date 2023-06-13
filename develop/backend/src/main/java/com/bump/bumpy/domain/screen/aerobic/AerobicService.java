package com.bump.bumpy.domain.screen.aerobic;

import com.bump.bumpy.database.entity.data.DataHAerobic;
import com.bump.bumpy.database.repository.data.DataHAerobicRepository;
import com.bump.bumpy.domain.screen.aerobic.dto.AerobicActivityResponseDto;
import com.bump.bumpy.domain.screen.aerobic.dto.DataHAerobicDto;
import com.bump.bumpy.domain.screen.dto.SearchDateRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AerobicService {

    private final DataHAerobicRepository aerobicRepository;


    public ResponseEntity<ResultMap> calendar() { return ResponseEntity.ok(new ResultMap()); }

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
