package com.bump.bumpy.domain.screen.inbody;

import com.bump.bumpy.domain.screen.dto.SearchDateRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchMonthRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchRequestDto;
import com.bump.bumpy.domain.screen.inbody.dto.DataHInbodyDto;
import com.bump.bumpy.domain.screen.inbody.dto.SearchInbodyDto;
import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

@RestController
@RequestMapping("/inbody")
@RequiredArgsConstructor
public class InbodyController {

    private final InbodyService inbodyService;

    @Operation(summary = "달력 조회", description = "")
    @GetMapping("/calendar")
    public ResponseEntity<ResultMap> calendar(SearchMonthRequestDto request) {
        request.setUserId(getUserId());
        return inbodyService.calendar(request);
    }

    @Operation(summary = "액티비티 조회", description = "")
    @GetMapping("/activity")
    public ResponseEntity<ResultMap> activity(SearchDateRequestDto request) {
        request.setUserId(getUserId());
        return inbodyService.activity(request);
    }

    @Operation(summary = "조회", description = "")
    @GetMapping("/search")
    public ResponseEntity<ResultMap> search(@Valid SearchInbodyDto request) {
        request.setUserId(getUserId());
        return inbodyService.search(request);
    }

    @Operation(summary = "추가", description = "")
    @PostMapping("/insert")
    public ResponseEntity<ResultMap> insert(@RequestBody DataHInbodyDto request) {
        String userId = getUserId();
        return inbodyService.insert(request, userId);
    }

    @Operation(summary = "수정", description = "")
    @PutMapping("/update")
    public ResponseEntity<ResultMap> update(@RequestBody DataHInbodyDto request) {
        String userId = getUserId();
        return inbodyService.update(request, userId);
    }

    @Operation(summary = "삭제", description = "")
    @DeleteMapping("/delete")
    public ResponseEntity<ResultMap> delete(SearchRequestDto request) {
        request.setUserId(getUserId());
        return inbodyService.delete(request);
    }
}
