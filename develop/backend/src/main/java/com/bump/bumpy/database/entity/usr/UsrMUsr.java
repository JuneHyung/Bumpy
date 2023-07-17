package com.bump.bumpy.database.entity.usr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USR_M_USR", schema = "bumpy", indexes = {
        @Index(name = "PRIMARY", columnList = "userId", unique = true)
})
public class UsrMUsr implements Serializable {
    private static final long serialVersionUID = 6465498083634823447L;
    @Id
    @Size(max = 20)
    @NotNull
    @Column(name = "userId", nullable = false, length = 20)
    @JsonIgnore
    private String userId;

    @Size(max = 100)
    @NotNull
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 100)
    @Column(name = "username", length = 100)
    private String username;

    @NotNull
    @Builder.Default
    @Column(name = "useYn", nullable = false)
    private Boolean useYn = true;

    @NotNull
    @Column(name = "gender", nullable = false)
    private Byte gender;

    @NotNull
    @Column(name = "birth", nullable = false)
    private Date birth;

    @Size(max = 20)
    @Column(name = "phoneNumber", length = 20)
    private String phoneNumber;

    @Size(max = 10)
    @Column(name = "zipCode", length = 10)
    private String zipCode;

    @Size(max = 200)
    @Column(name = "address", length = 200)
    private String address;

    @Size(max = 200)
    @Column(name = "addressDetail", length = 200)
    private String addressDetail;

    @Size(max = 1000)
    @Column(name = "picture", length = 1000)
    private String picture;
}