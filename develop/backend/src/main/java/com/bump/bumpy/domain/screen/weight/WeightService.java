package com.bump.bumpy.domain.screen.weight;

import com.bump.bumpy.database.entity.data.DataHWeight;
import com.bump.bumpy.database.repository.data.DataHWeightRepository;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.weight.dto.DataHWeightDto;
import com.bump.bumpy.util.dto.ResultMap;
import com.bump.bumpy.util.funtion.FieldValueUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.bump.bumpy.util.funtion.FieldValueUtil.isTodayDate;

@Service
@RequiredArgsConstructor
public class WeightService {

    private final DataHWeightRepository dataHWeightRepository;

    public ResponseEntity<ResultMap> calendar() { return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> activity() { return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        if(request.getSeq() == null) {
            return ResponseEntity.ok(new ResultMap(dataHWeightRepository.findByStdDateAndUserIdOrderBySeqAsc(request.getStdDate(), request.getUserId())));
        } else {
            return ResponseEntity.ok(new ResultMap(dataHWeightRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null)));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHWeightDto request, String userId) {
        if(!isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        if(dataHWeightRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 데이터입니다.");
        }

        DataHWeight dataHWeight = request.toEntity();
        dataHWeight.setUserId(userId);

        dataHWeightRepository.save(dataHWeight);

        return ResponseEntity.ok(new ResultMap("message", "저장되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> update(DataHWeightDto request, String userId) {
        if(!isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        DataHWeight dataHWeight = dataHWeightRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));

        dataHWeight = request.updateEntity(dataHWeight);

        dataHWeightRepository.save(dataHWeight);

        return ResponseEntity.ok(new ResultMap("message", "수정되었습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        if(!isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        DataHWeight dataHWeight = dataHWeightRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));

        dataHWeightRepository.delete(dataHWeight);

        return ResponseEntity.ok(new ResultMap("message", "삭제되었습니다."));
    }
}
