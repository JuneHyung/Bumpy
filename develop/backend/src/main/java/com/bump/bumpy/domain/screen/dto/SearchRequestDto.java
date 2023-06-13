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
public class SearchRequestDto {

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(example = "기준일자(시작)")
    Date stdDate;

    @NotNull
    @Builder.Default
    @ApiModelProperty(example = "시퀀스")
    Integer seq = null;

    @Builder.Default
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(example = "완료일자(종료)")
    Date endDate = null;

    @Hidden
    @ApiModelProperty(example = "유저 아이디 정보")
    String userId;
}
