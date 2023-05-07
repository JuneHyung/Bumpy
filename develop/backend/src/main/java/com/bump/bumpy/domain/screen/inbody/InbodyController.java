package com.bump.bumpy.domain.screen.inbody;

import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inbody")
@RequiredArgsConstructor
public class InbodyController {

    private final InbodyService inbodyService;

    @Operation(summary = "조회", description = "")
    @GetMapping("/search")
    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        return inbodyService.search(request);
    }

    @Operation(summary = "추가", description = "")
    @PostMapping("/insert")
    public ResponseEntity<ResultMap> insert() {
        return inbodyService.insert();
    }

    @Operation(summary = "수정", description = "")
    @PostMapping("/update")
    public ResponseEntity<ResultMap> update() {
        return inbodyService.update();
    }

    @Operation(summary = "삭제", description = "")
    @GetMapping("/delete")
    public ResponseEntity<ResultMap> delete() {
        return inbodyService.delete();
    }
}
