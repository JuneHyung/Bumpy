package com.bump.bumpy.domain.screen.aerobic.dto;

import com.bump.bumpy.database.entity.data.DataHAerobic;
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
 * DTO for {@link DataHAerobic}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AerobicActivityResponseDto implements Serializable {
    @NotNull
    private Date stdDate;
    @NotNull
    private Integer seq;
    @NotNull
    @Size(max = 100)
    private String name;
    private BigDecimal kcal;
    private Integer time;
    private BigDecimal inclineStart;
    private BigDecimal inclineEnd;
    private BigDecimal speedStart;
    private BigDecimal speedEnd;
    private String memo;

    // constructor from entity
    public AerobicActivityResponseDto(DataHAerobic entity) {
        this.stdDate = entity.getStdDate();
        this.seq = entity.getSeq();
        this.name = entity.getName();
        this.kcal = entity.getKcal();
        this.time = entity.getTime();
        this.inclineStart = entity.getInclineStart();
        this.inclineEnd = entity.getInclineEnd();
        this.speedStart = entity.getSpeedStart();
        this.speedEnd = entity.getSpeedEnd();
        this.memo = entity.getMemo();
    }
}