package com.bump.bumpy.domain.screen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchYoutubeDto {
    @NotNull
    @ApiModelProperty(example = "검색 키워드")
    String keyword;
}
