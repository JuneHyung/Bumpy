package com.bump.bumpy.database.entity;

import com.bump.bumpy.database.entity.composite.DataHWeightId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DATA_H_WEIGHT", schema = "bumpy", indexes = {
        @Index(name = "PRIMARY", columnList = "stdDate, userId, seq", unique = true),
        @Index(name = "WEIGHT_USER_ID_idx", columnList = "userId")
})
@IdClass(DataHWeightId.class)
public class DataHWeight implements Serializable {
    private static final long serialVersionUID = 74026321731262616L;

    @Id
    @NotNull
    @Column(name = "stdDate", nullable = false)
    private LocalDate stdDate;

    @Id
    @Size(max = 20)
    @NotNull
    @Column(name = "userId", nullable = false, length = 20)
    private String userId;

    @Id
    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "weightStart", precision = 10)
    private BigDecimal weightStart;

    @Column(name = "weightEnd", precision = 10)
    private BigDecimal weightEnd;

    @Column(name = "repsStart")
    private Integer repsStart;

    @Column(name = "repsEnd")
    private Integer repsEnd;

    @Column(name = "pollWeight", precision = 10)
    private BigDecimal pollWeight;

    @Column(name = "setReps")
    private Integer setReps;

    @NotNull
    @Column(name = "measure", nullable = false)
    private Integer measure;

    @Size(max = 1000)
    @Column(name = "memo", length = 1000)
    private String memo;

    @Size(max = 1000)
    @Column(name = "picture", length = 1000)
    private String picture;

}