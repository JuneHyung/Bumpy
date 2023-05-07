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
    @GetMapping("/userInfo")
    public ResponseEntity<ResultMap> userInfo() {
        return mainService.userInfo();
    }

    @Operation(summary = "추가", description = "")
    @GetMapping("/mealInfo")
    public ResponseEntity<ResultMap> mealInfo() {
        return mainService.mealInfo();
    }

    @Operation(summary = "수정", description = "")
    @GetMapping("/activityInfo")
    public ResponseEntity<ResultMap> activityInfo() {
        return mainService.activityInfo();
    }

}
