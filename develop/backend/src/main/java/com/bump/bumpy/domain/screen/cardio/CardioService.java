package com.bump.bumpy.domain.screen.cardio;

import com.bump.bumpy.database.entity.data.DataHCardio;
import com.bump.bumpy.database.repository.data.DataHCardioRepository;
import com.bump.bumpy.domain.screen.cardio.dto.DataHCardioDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardioService {

    private final DataHCardioRepository cardioRepository;


    public ResponseEntity<ResultMap> calendar() { return ResponseEntity.ok(new ResultMap()); }

    public ResponseEntity<ResultMap> activity() { return ResponseEntity.ok(new ResultMap()); }

    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        DataHCardio dataHCardio = cardioRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null);
        if(dataHCardio != null) {
            return ResponseEntity.ok(new ResultMap(dataHCardio));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHCardioDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다."); // 400 Bad Request
        }

        if(cardioRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 등록된 데이터입니다."));
        }

        DataHCardio dataHCardio = request.toEntity();
        dataHCardio.setUserId(userId);

        cardioRepository.save(dataHCardio);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultMap("message", "저장되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> update(@NotNull DataHCardioDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다."); // 400 Bad Request
        }

        DataHCardio dataHCardio = cardioRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).orElse(null);

        if(dataHCardio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        } else {
            dataHCardio = request.updateEntity(dataHCardio);

            cardioRepository.save(dataHCardio);

            return ResponseEntity.ok(new ResultMap("message", "수정되었습니다."));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }
        DataHCardio dataHCardio = cardioRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq())
                .orElse(null);

        if(dataHCardio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
        } else {
            cardioRepository.delete(dataHCardio);
            return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
        }
    }
}
