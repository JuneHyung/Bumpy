package com.bump.bumpy.domain.main;

import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    /*
        일요일부터 토요일까지 7개의 객체가 하나의 리스트를 이루고
        4줄 5줄 6줄 이 가능함
        [
            date:'' flag:false
            date:'' flag:false
            date:'' flag:false
            date:'' flag:false
            date:2023-02-01 flag:true
            date:2023-02-02 flag:true
            date:2023-02-03 flag:false
        ], ...
     */
    
    // 캘린더 API 추가하기

    @Operation(summary = "조회", description = "")
    @GetMapping("/userinfo")
    public ResponseEntity<ResultMap> userInfo() {
        String userId = getUserId();
        return mainService.userInfo(userId);
    }

    @Operation(summary = "식단정보", description = "")
    @GetMapping("/mealinfo")
    public ResponseEntity<ResultMap> mealInfo() {
        return mainService.mealInfo();
    }

    // 요청하면 알아서 최근날짜랑 데이터 같이 보내기 (액티비티랑, 에어로빅)
    // API 두개로 쪼개기
    @Operation(summary = "액티비티정보 - 근력", description = "")
    @GetMapping("/activityinfo/weight")
    public ResponseEntity<ResultMap> activityInfoWeight() {
        return mainService.activityInfoWeight();
    }

    @Operation(summary = "액티비티정보 - 유산소", description = "")
    @GetMapping("/activityinfo/aerobic")
    public ResponseEntity<ResultMap> activityInfoAerobic() {
        return mainService.activityInfoAerobic();
    }

    /*
        My Best, Month Avg, Reps Avg
        {
            categories : 운동을 한 날짜들
            series : 날짜들에 대응되는 데이터
        }
    */
    // API 두개로 쪼개기
    @Operation(summary = "차트 데이터", description = "")
    @GetMapping("/chart")
    public ResponseEntity<ResultMap> chart() {
        return mainService.chart();
    }

}
