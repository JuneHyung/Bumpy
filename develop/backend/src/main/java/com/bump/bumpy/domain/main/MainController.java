package com.bump.bumpy.domain.main;

import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private MainService mainService;

    @Operation(summary = "조회", description = "")
    @GetMapping("/userinfo")
    public ResponseEntity<ResultMap> userInfo() {
        return mainService.userInfo();
    }

    @Operation(summary = "식단정보", description = "")
    @GetMapping("/mealinfo")
    public ResponseEntity<ResultMap> mealInfo() {
        return mainService.mealInfo();
    }

    @Operation(summary = "액티비티정보", description = "")
    @GetMapping("/activityinfo")
    public ResponseEntity<ResultMap> activityInfo() {
        return mainService.activityInfo();
    }

    @Operation(summary = "차트 데이터", description = "")
    @GetMapping("/chart")
    public ResponseEntity<ResultMap> chart() {
        return mainService.chart();
    }

}
