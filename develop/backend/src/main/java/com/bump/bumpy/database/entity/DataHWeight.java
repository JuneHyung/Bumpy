package com.bump.bumpy.database.entity;

import com.bump.bumpy.database.entity.composite.DataHWeightId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "countStart")
    private Integer countStart;

    @Column(name = "countEnd")
    private Integer countEnd;

    @Column(name = "pollWeight", precision = 10)
    private BigDecimal pollWeight;

    @Column(name = "setCount")
    private Integer setCount;

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