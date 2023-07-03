package com.bump.bumpy.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "USER_DATA_M", schema = "bumpy")
public class UserData implements Serializable {
    private static final long serialVersionUID = -3107051566821694129L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userKey;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "gender")
    private String gender;

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