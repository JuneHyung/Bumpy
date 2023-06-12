package com.bump.bumpy.util.dto;

import com.bump.bumpy.database.entity.data.DataHCardio;
import com.bump.bumpy.util.customannotation.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link DataHCardio} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Password
public class PasswordDto {
    @Size(max = 100)
    @NotNull
    private String password;

    private String oldPassword;
}