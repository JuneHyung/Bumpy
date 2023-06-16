package com.bump.bumpy.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartRequestDto {
    @Size(max = 100)
    @NotNull
    private String name;
}
