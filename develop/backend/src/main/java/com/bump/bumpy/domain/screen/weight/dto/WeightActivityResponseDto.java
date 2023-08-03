package com.bump.bumpy.domain.screen.weight.dto;

import com.bump.bumpy.database.entity.data.DataHWeight;
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
 * DTO for {@link DataHWeight}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WeightActivityResponseDto implements Serializable {
    @NotNull
    private Date stdDate;
    @NotNull
    private Integer seq;
    @NotNull
    @Size(max = 100)
    private String name;
    private BigDecimal weightStart;
    private BigDecimal weightEnd;
    private Integer repsStart;
    private Integer repsEnd;
    private BigDecimal pollWeight;
    private Integer setReps;
    @NotNull
    private Integer measure;
    private String memo;

    // constructor from entity
    public WeightActivityResponseDto(DataHWeight entity) {
        this.stdDate = entity.getStdDate();
        this.seq = entity.getSeq();
        this.name = entity.getName();
        this.weightStart = entity.getWeightStart();
        this.weightEnd = entity.getWeightEnd();
        this.repsStart = entity.getRepsStart();
        this.repsEnd = entity.getRepsEnd();
        this.pollWeight = entity.getPollWeight();
        this.setReps = entity.getSetReps();
        this.measure = entity.getMeasure();
        this.memo = entity.getMemo();
    }
}