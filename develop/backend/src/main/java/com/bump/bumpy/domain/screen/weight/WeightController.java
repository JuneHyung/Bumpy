package com.bump.bumpy.domain.screen.weight;

import com.bump.bumpy.domain.screen.dto.SearchDateRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.weight.dto.DataHWeightDto;
import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

@RestController
@RequestMapping("/weight")
@RequiredArgsConstructor
public class WeightController {

    private final WeightService weightService;

    @Operation(summary = "달력 조회", description = "")
    @GetMapping("/calendar")
    public ResponseEntity<ResultMap> calendar() {
        return weightService.calendar();
    }

    @Operation(summary = "액티비티 조회", description = "")
    @GetMapping("/activity")
    public ResponseEntity<ResultMap> activity(SearchDateRequestDto request) {
        request.setUserId(getUserId());
        return weightService.activity(request);
    }

    @Operation(summary = "조회", description = "")
    @GetMapping("/search")
    public ResponseEntity<ResultMap> search(@Valid SearchRequestDto request) {
        request.setUserId(getUserId());
        return weightService.search(request);
    }

    @Operation(summary = "추가", description = "")
    @PostMapping("/insert")
    public ResponseEntity<ResultMap> insert(@RequestBody DataHWeightDto request) {
        String userId = getUserId();
        return weightService.insert(request, userId);
    }

    @Operation(summary = "수정", description = "")
    @PutMapping("/update")
    public ResponseEntity<ResultMap> update(@RequestBody DataHWeightDto request) {
        String userId = getUserId();
        return weightService.update(request, userId);
    }

    @Operation(summary = "삭제", description = "")
    @DeleteMapping("/delete")
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        request.setUserId(getUserId());
        return weightService.delete(request);
    }
}
