package com.bump.bumpy.domain.auth.dto.request;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Size(min = 5, max = 20)
    @NotNull
    @ApiModelProperty(value = "아이디", dataType = "String", example = "id")
    String userId;

    @Size(min = 8, max = 20)
    @NotNull
    @ApiModelProperty(value = "패스워드", dataType = "String", example = "password")
    String password;
}
