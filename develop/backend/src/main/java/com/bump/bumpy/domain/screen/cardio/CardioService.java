package com.bump.bumpy.domain.screen.cardio;

import com.bump.bumpy.database.entity.data.DataHCardio;
import com.bump.bumpy.database.repository.data.DataHCardioRepository;
import com.bump.bumpy.domain.screen.cardio.dto.DataHCardioDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardioService {

    private final DataHCardioRepository cardioRepository;


    public ResponseEntity<ResultMap> calendar() { return ResponseEntity.ok(new ResultMap()); }

    public ResponseEntity<ResultMap> activity() { return ResponseEntity.ok(new ResultMap()); }

    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        if(request.getSeq() == null) {
            return ResponseEntity.ok(new ResultMap(cardioRepository.findByStdDateAndUserIdOrderBySeqAsc(request.getStdDate(), request.getUserId())));
        } else {
            return ResponseEntity.ok(new ResultMap(cardioRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null)));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHCardioDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        if(cardioRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 데이터입니다.");
        }

        DataHCardio dataHCardio = request.toEntity();
        dataHCardio.setUserId(userId);

        cardioRepository.save(dataHCardio);

        return ResponseEntity.ok(new ResultMap("message", "저장되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> update(@NotNull DataHCardioDto request, String userId) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        DataHCardio dataHCardio = cardioRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));

        dataHCardio = request.updateEntity(dataHCardio);

        cardioRepository.save(dataHCardio);

        return ResponseEntity.ok(new ResultMap("message", "수정되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        if(!FieldValueUtil.isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }
        DataHCardio dataHCardio = cardioRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));
        cardioRepository.delete(dataHCardio);

        return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
    }

}
