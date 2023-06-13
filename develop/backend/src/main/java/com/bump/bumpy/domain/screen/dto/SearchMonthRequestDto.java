package com.bump.bumpy.domain.screen.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class SearchMonthRequestDto {

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(example = "기준일자(시작)")
    Date stdDate;

    @Hidden
    @ApiModelProperty(example = "유저 아이디 정보")
    String userId;
}
