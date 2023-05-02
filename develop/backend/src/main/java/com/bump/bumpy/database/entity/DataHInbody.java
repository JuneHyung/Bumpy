package com.bump.bumpy.database.entity;

import com.bump.bumpy.database.entity.composite.DataHInbodyId;
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
@Table(name = "DATA_H_INBODY", schema = "bumpy", indexes = {
        @Index(name = "INBODY_USER_ID_idx", columnList = "userId"),
        @Index(name = "PRIMARY", columnList = "stdDate, userId", unique = true)
})
@IdClass(DataHInbodyId.class)
public class DataHInbody implements Serializable {
    private static final long serialVersionUID = 3364908228672838984L;

    @Id
    @NotNull
    @Column(name = "stdDate", nullable = false)
    private LocalDate stdDate;

    @Id
    @Size(max = 20)
    @NotNull
    @Column(name = "userId", nullable = false, length = 20)
    private String userId;

    @Column(name = "height", precision = 10)
    private BigDecimal height;

    @Column(name = "weight", precision = 10)
    private BigDecimal weight;

    @Column(name = "muscle", precision = 10)
    private BigDecimal muscle;

    @Column(name = "fat", precision = 10)
    private BigDecimal fat;

    @Column(name = "score", precision = 10)
    private BigDecimal score;

    @Column(name = "bmi", precision = 10)
    private BigDecimal bmi;

    @Column(name = "fatPercent", precision = 10)
    private BigDecimal fatPercent;

    @Size(max = 1000)
    @Column(name = "picture", length = 1000)
    private String picture;

}