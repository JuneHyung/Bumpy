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
 * A DTO for the {@link com.bump.bumpy.database.entity.data.DataHWeight} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DataHWeightDto implements Serializable {
    @NotNull
    private Date stdDate;
    @NotNull
    private Integer seq;
    @Size(max = 100)
    @NotNull
    private String name;
    private BigDecimal weightStart;
    private BigDecimal weightEnd;
    private Integer repsStart;
    private Integer repsEnd;
    private BigDecimal pollWeight;
    private Integer setReps;
    @NotNull
    private Integer measure;
    @Size(max = 1000)
    private String memo;
    @Size(max = 1000)
    private String picture;

    // to entity method for insert
    public DataHWeight toEntity() {
        DataHWeight entity = new DataHWeight();
        entity.setStdDate(this.stdDate);
        entity.setSeq(this.seq);
        entity.setName(this.name);
        entity.setWeightStart(this.weightStart);
        entity.setWeightEnd(this.weightEnd);
        entity.setRepsStart(this.repsStart);
        entity.setRepsEnd(this.repsEnd);
        entity.setPollWeight(this.pollWeight);
        entity.setSetReps(this.setReps);
        entity.setMeasure(this.measure);
        entity.setMemo(this.memo);
        entity.setPicture(this.picture);
        return entity;
    }

    // to entity method for update
    public DataHWeight updateEntity(DataHWeight entity) {
        entity.setName(this.name);
        entity.setWeightStart(this.weightStart);
        entity.setWeightEnd(this.weightEnd);
        entity.setRepsStart(this.repsStart);
        entity.setRepsEnd(this.repsEnd);
        entity.setPollWeight(this.pollWeight);
        entity.setSetReps(this.setReps);
        entity.setMeasure(this.measure);
        entity.setMemo(this.memo);
        entity.setPicture(this.picture);
        return entity;
    }
}