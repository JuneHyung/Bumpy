package com.bump.bumpy.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "temp_email_auth_test1", schema = "bumpy")
public class TempEmailAuthTest1 implements Serializable {
    private static final long serialVersionUID = -4355458508487294356L;
    @Id
    @Size(max = 100)
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "exp_date", nullable = false)
    private Date expDate;

    @Size(max = 45)
    @Column(name = "email_code", length = 45)
    private String emailCode;

    @Size(max = 100)
    @Column(name = "accessToken", length = 100)
    private String accessToken;

}