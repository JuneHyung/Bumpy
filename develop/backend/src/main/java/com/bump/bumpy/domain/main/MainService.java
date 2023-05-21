package com.bump.bumpy.domain.main;

import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

    public ResponseEntity<ResultMap> userInfo() {
        return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> mealInfo() {
        return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> activityInfo() {
        return ResponseEntity.ok(new ResultMap());
    }

    public ResponseEntity<ResultMap> chart() { return ResponseEntity.ok(new ResultMap()); }
}
