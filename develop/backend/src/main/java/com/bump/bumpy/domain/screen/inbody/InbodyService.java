package com.bump.bumpy.domain.screen.inbody;

import com.bump.bumpy.database.entity.data.DataHInbody;
import com.bump.bumpy.database.repository.data.DataHInbodyRepository;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.inbody.dto.DataHInbodyDto;
import com.bump.bumpy.domain.screen.inbody.dto.SearchInbodyDto;
import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class InbodyService {

    private final DataHInbodyRepository dataHInbodyRepository;

    public ResponseEntity<ResultMap> search(SearchInbodyDto request) {
        DataHInbody dataHInbody = dataHInbodyRepository.findByStdDateAndUserId(request.getStdDate(), request.getUserId()).orElse(null);
        if(dataHInbody == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(new ResultMap(dataHInbody));
        }
    }

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

        DataHInbody dataHInbody = request.toEntity();
        dataHInbody.setUserId(userId);

        dataHInbodyRepository.save(dataHInbody);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultMap("message", "저장되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> update(DataHInbodyDto request, String userId) {
        DataHInbody dataHInbody = dataHInbodyRepository.findByStdDateAndUserId(request.getStdDate(), userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));

        dataHInbody = request.updateEntity(dataHInbody);

        dataHInbodyRepository.save(dataHInbody);
        return ResponseEntity.ok(new ResultMap("message", "수정되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        DataHInbody dataHInbody = dataHInbodyRepository.findByStdDateAndUserId(request.getStdDate(), request.getUserId()).orElse(null);
        if(dataHInbody == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "해당 데이터가 없습니다."));
        }
        dataHInbodyRepository.delete(dataHInbody);
        return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
    }
}
