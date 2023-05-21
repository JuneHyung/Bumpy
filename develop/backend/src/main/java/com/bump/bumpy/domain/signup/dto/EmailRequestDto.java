package com.bump.bumpy.domain.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDto {
    private String email;
    private String userId;
    private String verifyCode;
}
