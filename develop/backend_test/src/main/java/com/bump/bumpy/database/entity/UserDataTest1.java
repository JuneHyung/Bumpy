package com.bump.bumpy.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_data_test1", schema = "bumpy")
public class UserDataTest1 implements Serializable {
    private static final long serialVersionUID = -2404353041686912459L;
    @Id
    @Column(name = "`key`", nullable = false)
    private Integer key;

    @NotNull
    @Column(name = "user_key", nullable = false)
    private Integer userKey;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "gender")
    private Character gender;

    @Size(max = 4)
    @Column(name = "birth_year", length = 4)
    private String birthYear;

    @Size(max = 2)
    @Column(name = "birth_month", length = 2)
    private String birthMonth;

    @Size(max = 2)
    @Column(name = "birth_day", length = 2)
    private String birthDay;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Size(max = 200)
    @Column(name = "address", length = 200)
    private String address;

}