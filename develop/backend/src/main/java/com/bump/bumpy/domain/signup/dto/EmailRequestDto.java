package com.bump.bumpy.domain.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDto {
    @NotEmpty
    private String email;
    @NotEmpty
    private String userId;
    private String verifyCode;
}
