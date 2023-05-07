package com.bump.bumpy.domain.screen.inbody;

import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InbodyService {

    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        return ResponseEntity.ok(new ResultMap());
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
