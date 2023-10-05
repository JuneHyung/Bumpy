package com.bump.bumpy.domain.signup.dto;

import com.bump.bumpy.database.entity.usr.UsrMUsr;
import com.bump.bumpy.util.dto.PasswordDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
//    @Size(max = 100)
//    @NotNull
//    private String password;
    @Size(max = 100)
    @NotNull
    private String email;
    @NotNull
    private Byte gender;
    @NotNull
    private Date birth;
    @Size(max = 20)
    private String phoneNumber;
    private Integer addressMail;
    @Size(max = 200)
    private String address;
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
        entity.setAddressMail(dto.getAddressMail());
        entity.setAddress(dto.getAddress());
        entity.setPicture(dto.getPicture());
        return entity;
    }
}