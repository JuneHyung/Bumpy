package com.bump.bumpy.domain.screen.cardio;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cardio")
@RequiredArgsConstructor
public class CardioController {

    private final CardioService cardioService;

    @Operation(summary = "달력 조회", description = "")
    @GetMapping("/calendar")
    public ResponseEntity<ResultMap> calendar(HttpServletRequest httpServletRequest) {
        return cardioService.calendar();
    }

    @Operation(summary = "액티비티 조회", description = "")
    @GetMapping("/activity")
    public ResponseEntity<ResultMap> activity(HttpServletRequest httpServletRequest) {
        return cardioService.activity();
    }

    @Operation(summary = "조회", description = "")
    @GetMapping("/search")
    public ResponseEntity<ResultMap> search(HttpServletRequest httpServletRequest, @RequestBody SearchRequestDto request) {
        String userId = getUserId(httpServletRequest);
        request.setUserId(getUserId(httpServletRequest));
        return cardioService.search(request);
    }

    @Operation(summary = "추가", description = "")
    @PostMapping("/insert")
    public ResponseEntity<ResultMap> insert(HttpServletRequest httpServletRequest) {
        String userId = getUserId(httpServletRequest);
        return cardioService.insert();
    }

    @Operation(summary = "수정", description = "")
    @PostMapping("/update")
    public ResponseEntity<ResultMap> update(HttpServletRequest httpServletRequest) {
        String userId = getUserId(httpServletRequest);
        return cardioService.update();
    }

    @Operation(summary = "삭제", description = "")
    @GetMapping("/delete")
    public ResponseEntity<ResultMap> delete(HttpServletRequest httpServletRequest) {
        String userId = getUserId(httpServletRequest);
        return cardioService.delete();
    }

    private String getUserId(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        return String.valueOf(session.getAttribute("userId"));
    }
}
