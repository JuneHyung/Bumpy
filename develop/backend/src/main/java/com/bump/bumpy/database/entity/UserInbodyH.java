package com.bump.bumpy.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@Table(name = "USER_INBODY_H", schema = "bumpy", indexes = {
        @Index(name = "userId_INDEX", columnList = "user_id"),
})
public class UserInbodyH implements Serializable {
    private static final long serialVersionUID = 8509340287155672114L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "d_date")
    private Date dDate;

    @Size(max = 10)
    @Column(name = "d_tall", length = 10)
    private String dTall;

    @Size(max = 10)
    @Column(name = "d_weight", length = 10)
    private String dWeight;

    @Size(max = 10)
    @Column(name = "d_muscle", length = 10)
    private String dMuscle;

    @Size(max = 10)
    @Column(name = "d_fat", length = 10)
    private String dFat;

    @Size(max = 10)
    @Column(name = "d_score", length = 10)
    private String dScore;

    @Size(max = 10)
    @Column(name = "d_bmi", length = 10)
    private String dBmi;

    @Size(max = 10)
    @Column(name = "d_fatPerWeight", length = 10)
    private String dFatperweight;

    @Size(max = 200)
    @Column(name = "d_picture", length = 200)
    private String dPicture;
}