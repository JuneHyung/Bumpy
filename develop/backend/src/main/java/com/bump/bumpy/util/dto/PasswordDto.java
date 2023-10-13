package com.bump.bumpy.util.dto;

import com.bump.bumpy.util.customannotation.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Password
public class PasswordDto {
    @Size(max = 100)
    @NotEmpty
    private String password;

    private String oldPassword;
}