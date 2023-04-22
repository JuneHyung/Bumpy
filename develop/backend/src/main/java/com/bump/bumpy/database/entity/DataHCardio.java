package com.bump.bumpy.database.entity;

import com.bump.bumpy.database.entity.composite.DataHCardioId;
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
@Table(name = "DATA_H_CARDIO", schema = "bumpy", indexes = {
        @Index(name = "PRIMARY", columnList = "stdDate, userId, seq", unique = true),
        @Index(name = "CARDIO_USER_ID_idx", columnList = "userId")
})
@IdClass(DataHCardioId.class)
public class DataHCardio implements Serializable {
    private static final long serialVersionUID = -3260140188077884176L;

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

    @Column(name = "kcal", precision = 10)
    private BigDecimal kcal;

    @Column(name = "time")
    private Integer time;

    @Column(name = "inclineStart", precision = 10)
    private BigDecimal inclineStart;

    @Column(name = "inclineEnd", precision = 10)
    private BigDecimal inclineEnd;

    @Column(name = "speedStart", precision = 10)
    private BigDecimal speedStart;

    @Column(name = "speedEnd", precision = 10)
    private BigDecimal speedEnd;

    @Size(max = 1000)
    @Column(name = "memo", length = 1000)
    private String memo;

    @Size(max = 1000)
    @Column(name = "picture", length = 1000)
    private String picture;

}