package com.bump.bumpy.domain.main.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartAerobicResponseDto {
    // TODO : Weight 차트 데이터 출력 ( 10일치 데이터 )
    // 1. My Best : 전체 기간 중 무게 최고값
    // 2. Chart Average : 10개 데이터의 평균값
    // 3. Reps When Best : My Best 때의 Reps와 Set값
    /*
    Series
    [
      {
        name: line이름
        data: [1, 4, 5, ...]
      },
    	...
    ]
     */

    private BigDecimal bestKcal;
    private Integer bestTime;
    private BigDecimal averageIncline;
    private BigDecimal averageSpeed;

    // 차트 X축 값
    @Setter(onMethod_ = {@JsonSetter("xAxis")}) // 소문자 뒤에 바로 대문자가 오는 경우 올바르게 인식하지 못함
    @Getter(onMethod_ = {@JsonGetter("xAxis")}) // 소문자 뒤에 바로 대문자가 오는 경우 올바르게 인식하지 못함
    private List<String> xAxis;
    // 차트 Y축 값( List<BigDecimal>의 원소 개수는 X축 값 개수와 동일 )
    private List<Map<String, Object>> series;
}
