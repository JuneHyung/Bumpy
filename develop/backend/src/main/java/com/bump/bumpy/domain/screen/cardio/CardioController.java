package com.bump.bumpy.domain.screen.cardio;

import com.bump.bumpy.domain.screen.cardio.dto.DataHCardioDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

@RestController
@RequestMapping("/cardio")
@RequiredArgsConstructor
public class CardioController {

    private final CardioService cardioService;

    @Operation(summary = "달력 조회", description = "")
    @GetMapping("/calendar")
    public ResponseEntity<ResultMap> calendar() {
        return cardioService.calendar();
    }

    @Operation(summary = "액티비티 조회", description = "")
    @GetMapping("/activity")
    public ResponseEntity<ResultMap> activity() {
        return cardioService.activity();
    }

    @Operation(summary = "조회", description = "")
    @GetMapping("/search")
    public ResponseEntity<ResultMap> search(SearchRequestDto request) {
        request.setUserId(getUserId());
        return cardioService.search(request);
    }

    @Operation(summary = "추가", description = "")
    @PostMapping("/insert")
    public ResponseEntity<ResultMap> insert(@RequestBody DataHCardioDto request) {
        String userId = getUserId();
        return cardioService.insert(request, userId);
    }

    @Operation(summary = "수정", description = "")
    @PostMapping("/update")
    public ResponseEntity<ResultMap> update(@RequestBody DataHCardioDto request) {
        String userId = getUserId();
        return cardioService.update(request, userId);
    }

    @Operation(summary = "삭제", description = "")
    @GetMapping("/delete")
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        request.setUserId(getUserId());
        return cardioService.delete(request);
    }
}
