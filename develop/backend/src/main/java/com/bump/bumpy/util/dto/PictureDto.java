package com.bump.bumpy.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto {
    private String name;
    private String data;
    private Long size;
}
