package com.bump.bumpy.domain.screen.aerobic;

import com.bump.bumpy.domain.screen.aerobic.dto.DataHAerobicDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
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
@RequestMapping("/cardio")
@RequiredArgsConstructor
public class AerobicController {

    private final AerobicService aerobicService;

    /*
    달력에 나갈 데이터 DTO 형태
    {date name}
    {date name}
    {date name}
    {date name}
    {date name}
    {date name}
    {date name}
    {date name}
    {date name}
    {date name}
     */

    @Operation(summary = "달력 조회", description = "")
    @GetMapping("/calendar")
    public ResponseEntity<ResultMap> calendar() {
        return aerobicService.calendar();
    }

    @Operation(summary = "액티비티 조회", description = "")
    @GetMapping("/activity")
    public ResponseEntity<ResultMap> activity() {
        return aerobicService.activity();
    }

    @Operation(summary = "조회", description = "")
    @GetMapping("/search")
    public ResponseEntity<ResultMap> search(@Valid SearchRequestDto request) {
        request.setUserId(getUserId());
        return aerobicService.search(request);
    }

    @Operation(summary = "추가", description = "")
    @PostMapping("/insert")
    public ResponseEntity<ResultMap> insert(@RequestBody DataHAerobicDto request) {
        String userId = getUserId();
        return aerobicService.insert(request, userId);
    }

    @Operation(summary = "수정", description = "")
    @PutMapping("/update")
    public ResponseEntity<ResultMap> update(@RequestBody DataHAerobicDto request) {
        String userId = getUserId();
        return aerobicService.update(request, userId);
    }

    @Operation(summary = "삭제", description = "")
    @DeleteMapping("/delete")
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        request.setUserId(getUserId());
        return aerobicService.delete(request);
    }
}
