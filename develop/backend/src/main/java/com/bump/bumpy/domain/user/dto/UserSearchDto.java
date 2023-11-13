package com.bump.bumpy.domain.user.dto;

import com.bump.bumpy.database.entity.usr.UsrMUsr;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.bump.bumpy.database.entity.usr.UsrMUsr} entity
 */
@Data
public class UserSearchDto implements Serializable {
    @Size(max = 20)
    @NotEmpty
    private final String userId;
    @Size(max = 100)
    @NotEmpty
    private final String email;
    @Size(max = 100)
    private final String username;
    @NotNull
    private final Byte gender;
    @NotNull
    private final Date birth;
    @Size(max = 20)
    private final String phoneNumber;
    @Size(max = 10)
    private final String zipCode;
    @Size(max = 200)
    private final String address;
    @Size(max = 200)
    private final String addressDetail;
    @Size(max = 1000)
    private final String picture;

    public UserSearchDto(UsrMUsr user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.gender = user.getGender();
        this.birth = user.getBirth();
        this.phoneNumber = user.getPhoneNumber();
        this.zipCode = user.getZipCode();
        this.address = user.getAddress();
        this.addressDetail = user.getAddressDetail();
        this.picture = user.getPicture();
    }
}