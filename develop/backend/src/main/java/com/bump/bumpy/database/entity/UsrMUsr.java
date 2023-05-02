package com.bump.bumpy.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

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
    private String userId;

    @Size(max = 20)
    @NotNull
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotNull
    @Builder.Default
    @Column(name = "useYn", nullable = false)
    private Boolean useYn = false;

    @NotNull
    @Column(name = "gender", nullable = false)
    private Byte gender;

    @NotNull
    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Size(max = 20)
    @Column(name = "phoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "addressMail")
    private Integer addressMail;

    @Size(max = 200)
    @Column(name = "address", length = 200)
    private String address;
}