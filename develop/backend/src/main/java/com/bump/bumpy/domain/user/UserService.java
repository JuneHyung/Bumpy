package com.bump.bumpy.domain.user;

import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    public ResponseEntity<ResultMap> check() { return ResponseEntity.ok(new ResultMap()); }

    public ResponseEntity<ResultMap> passwordCheck() { return ResponseEntity.ok(new ResultMap()); }

    public ResponseEntity<ResultMap> update() { return ResponseEntity.ok(new ResultMap()); }

    public ResponseEntity<ResultMap> delete() { return ResponseEntity.ok(new ResultMap()); }
}
