package com.bump.bumpy.database.entity.data;

import com.bump.bumpy.database.entity.composite.DataHMealId;
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
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DATA_H_MEAL", schema = "bumpy", indexes = {
        @Index(name = "PRIMARY", columnList = "stdDate, userId, seq", unique = true),
        @Index(name = "userId_idx", columnList = "userId")
})
@IdClass(DataHMealId.class)
public class DataHMeal implements Serializable {
    private static final long serialVersionUID = 4652796048371446150L;

    @Id
    @NotNull
    @Column(name = "stdDate", nullable = false)
    private Date stdDate;

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

    @Column(name = "`order`")
    private Integer order;

    @Column(name = "time")
    private Date time;

    @Column(name = "kcal", precision = 10)
    private BigDecimal kcal;

    @Column(name = "water", precision = 10)
    private BigDecimal water;

    @Size(max = 1000)
    @Column(name = "memo", length = 1000)
    private String memo;

    @Size(max = 1000)
    @Column(name = "picture", length = 1000)
    private String picture;

    @Size(max = 100)
    @Column(name = "food1", length = 100)
    private String food1;

    @Size(max = 100)
    @Column(name = "food2", length = 100)
    private String food2;

    @Size(max = 100)
    @Column(name = "food3", length = 100)
    private String food3;

    @Size(max = 100)
    @Column(name = "food4", length = 100)
    private String food4;

    @Size(max = 100)
    @Column(name = "food5", length = 100)
    private String food5;

    @Size(max = 100)
    @Column(name = "food6", length = 100)
    private String food6;

    @Size(max = 100)
    @Column(name = "food7", length = 100)
    private String food7;

    @Size(max = 100)
    @Column(name = "food8", length = 100)
    private String food8;

    @Size(max = 100)
    @Column(name = "food9", length = 100)
    private String food9;

    @Size(max = 100)
    @Column(name = "food10", length = 100)
    private String food10;
}