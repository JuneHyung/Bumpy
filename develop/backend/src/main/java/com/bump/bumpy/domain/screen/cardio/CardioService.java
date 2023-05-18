package com.bump.bumpy.domain.screen.cardio;

import com.bump.bumpy.database.entity.data.DataHCardio;
import com.bump.bumpy.database.repository.data.DataHCardioRepository;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardioService {

    private final DataHCardioRepository cardioRepository;


    public ResponseEntity<ResultMap> calendar() { return ResponseEntity.ok(new ResultMap()); }

    public ResponseEntity<ResultMap> activity() { return ResponseEntity.ok(new ResultMap()); }

    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        List<DataHCardio> list = new ArrayList<>();
        if(request.getSeq() == null) {
            list = cardioRepository.findByStdDateAndUserIdOrderBySeqAsc(request.getStdDate(), request.getUserId());
        } else {
            DataHCardio dataHCardio = cardioRepository.findByStdDateAndUserIdAndSeq(request.getStdDate(), request.getUserId(), request.getSeq()).orElse(null);
            if(dataHCardio != null) {
                list.add(dataHCardio);
            }
        }
        return ResponseEntity.ok(new ResultMap(list));
    }

    public ResponseEntity<ResultMap> insert() {
        return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> update() {
        return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> delete() {
        return ResponseEntity.ok(new ResultMap());
    }

}
