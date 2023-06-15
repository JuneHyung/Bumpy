package com.bump.bumpy.domain.signup.dto;

import com.bump.bumpy.database.entity.usr.UsrMUsr;
import com.bump.bumpy.util.dto.PasswordDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.bump.bumpy.database.entity.usr.UsrMUsr} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UsrMUsrDto extends PasswordDto implements Serializable{
    @Size(max = 20)
    @NotNull
    private String userId;

    // password 변수는 PasswordDto 클래스에서 상속

    @Size(max = 100)
    @NotNull
    private String email;
    @NotNull
    private Byte gender;
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;
    @Size(max = 20)
    private String phoneNumber;
    private String zipCode;
    @Size(max = 200)
    private String address;
    @Size(max = 200)
    private String addressDetail;
    @Size(max = 1000)
    private String picture;

    // to entity method for insert
    public UsrMUsr toEntity(UsrMUsrDto dto) {
        UsrMUsr entity = new UsrMUsr();
        entity.setUserId(dto.getUserId());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setGender(dto.getGender());
        entity.setBirth(dto.getBirth());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setZipCode(dto.getZipCode());
        entity.setAddress(dto.getAddress());
        entity.setAddressDetail(dto.getAddressDetail());
        entity.setPicture(dto.getPicture());
        return entity;
    }
}