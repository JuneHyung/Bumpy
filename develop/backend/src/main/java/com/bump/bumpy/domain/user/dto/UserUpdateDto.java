package com.bump.bumpy.domain.user.dto;

import com.bump.bumpy.database.entity.usr.UsrMUsr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * A DTO for the {@link UsrMUsr} entity
 */
@Getter
@Setter
@AllArgsConstructor
public class UserUpdateDto {

    @Size(max = 100)
    private String password;

//    @Size(max = 100)
//    @Builder.Default
//    private String email = null;
    private Byte gender = null;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth = null;
    @Size(max = 20)
    private String phoneNumber = null;
    private String zipCode = null;
    @Size(max = 200)
    private String address = null;
    @Size(max = 200)
    private String addressDetail = null;
    @Size(max = 1000)
    private String picture = null;

    // to entity method for update
    public UsrMUsr updateEntity(UsrMUsr entity) {
        // null check every field
//        if (this.getEmail() != null) entity.setEmail(this.getEmail());
        if(this.getGender() != null) entity.setGender(this.getGender());
        if(this.getBirth() != null) entity.setBirth(this.getBirth());
        if(this.getPhoneNumber() != null){
            if(this.getPhoneNumber().equals("")) entity.setPhoneNumber(null);
            else entity.setPhoneNumber(this.getPhoneNumber());
        }
        if(this.getZipCode() != null) {
            if(this.getZipCode().equals("")) entity.setZipCode(null);
            else entity.setZipCode(this.getZipCode());
        }
        if(this.getAddress() != null) {
            if(this.getAddress().equals("")) entity.setAddress(null);
            else entity.setAddress(this.getAddress());
        }
        if(this.getAddressDetail() != null) {
            if(this.getAddressDetail().equals("")) entity.setAddressDetail(null);
            else entity.setAddressDetail(this.getAddressDetail());
        }
        if(this.getPicture() != null) {
            if(this.getPicture().equals("")) entity.setPicture(null);
            else entity.setPicture(this.getPicture());
        }
        return entity;
    }
}