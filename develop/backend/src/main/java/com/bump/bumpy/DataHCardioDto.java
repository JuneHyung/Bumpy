package com.bump.bumpy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A DTO for the {@link com.bump.bumpy.database.entity.data.DataHCardio} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DataHCardioDto implements Serializable {
    @NotNull
    private Date stdDate;
    @Size(max = 20)
    @NotNull
    private String userId;
    @NotNull
    private Integer seq;
    @Size(max = 100)
    @NotNull
    private String name;
    private BigDecimal kcal;
    private Integer time;
    private BigDecimal inclineStart;
    private BigDecimal inclineEnd;
    private BigDecimal speedStart;
    private BigDecimal speedEnd;
    @Size(max = 1000)
    private String memo;
    @Size(max = 1000)
    private String picture;
}