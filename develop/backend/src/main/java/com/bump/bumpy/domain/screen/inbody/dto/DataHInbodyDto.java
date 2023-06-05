package com.bump.bumpy.domain.screen.inbody.dto;

import com.bump.bumpy.database.entity.data.DataHInbody;
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
 * A DTO for the {@link com.bump.bumpy.database.entity.data.DataHInbody} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DataHInbodyDto implements Serializable {
    @NotNull
    private Date stdDate;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal muscle;
    private BigDecimal fat;
    private BigDecimal score;
    private BigDecimal bmi;
    private BigDecimal fatRate;
    @Size(max = 1000)
    private String picture;

    // to entity method for insert
    public DataHInbody toEntity() {
        DataHInbody entity = new DataHInbody();
        entity.setStdDate(this.stdDate);
        entity.setHeight(this.height);
        entity.setWeight(this.weight);
        entity.setMuscle(this.muscle);
        entity.setFat(this.fat);
        entity.setScore(this.score);
        entity.setBmi(this.bmi);
        entity.setFatRate(this.fatRate);
        entity.setPicture(this.picture);
        return entity;
    }

    // to entity method for update
    public DataHInbody updateEntity(DataHInbody entity) {
        entity.setHeight(this.height);
        entity.setWeight(this.weight);
        entity.setMuscle(this.muscle);
        entity.setFat(this.fat);
        entity.setScore(this.score);
        entity.setBmi(this.bmi);
        entity.setFatRate(this.fatRate);
        entity.setPicture(this.picture);
        return entity;
    }
}