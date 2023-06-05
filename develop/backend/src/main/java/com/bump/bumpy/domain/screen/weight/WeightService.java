package com.bump.bumpy.domain.screen.weight;

import com.bump.bumpy.database.entity.data.DataHWeight;
import com.bump.bumpy.database.repository.data.DataHWeightRepository;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.weight.dto.DataHWeightDto;
import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            List<DataHWeight> dataHWeightList = dataHWeightRepository.findByStdDateAndUserIdOrderBySeqAsc(request.getStdDate(), request.getUserId());
            if(dataHWeightList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResultMap("message", "데이터가 없습니다."));
            } else {
                return ResponseEntity.ok(new ResultMap(dataHWeightList));
            }
        } else {
            DataHWeight dataHWeight = dataHWeightRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null);
            if(dataHWeight == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultMap("message", "데이터가 없습니다."));
            } else {
                return ResponseEntity.ok(new ResultMap(dataHWeight));
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> insert(DataHWeightDto request, String userId) {
        if(!isTodayDate(request.getStdDate())) {
            throw new IllegalArgumentException("날짜가 오늘이 아닙니다.");
        }

        if(dataHWeightRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), userId, request.getSeq()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 데이터가 존재합니다."));
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
