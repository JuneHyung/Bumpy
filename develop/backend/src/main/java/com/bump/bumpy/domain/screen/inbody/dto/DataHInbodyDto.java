package com.bump.bumpy.domain.screen.inbody.dto;

import com.bump.bumpy.database.entity.data.DataHInbody;
import com.bump.bumpy.util.dto.PictureDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    @NotNull
    private BigDecimal height;
    @NotNull
    private BigDecimal weight;
    @NotNull
    private BigDecimal muscle;
    @NotNull
    private BigDecimal fat;
    private BigDecimal score;
    private BigDecimal bmi;
    private BigDecimal fatRate;
    private List<PictureDto> picture;

    // to entity method for insert
    public DataHInbody toEntity(List<String> picture) {
        DataHInbody entity = new DataHInbody();
        entity.setStdDate(this.stdDate);
        entity.setHeight(this.height);
        entity.setWeight(this.weight);
        entity.setMuscle(this.muscle);
        entity.setFat(this.fat);
        entity.setScore(this.score);
        entity.setBmi(this.bmi);
        entity.setFatRate(this.fatRate);
        entity.setPicture(picture);
        return entity;
    }

    // to entity method for update
    public DataHInbody updateEntity(DataHInbody entity, List<String> picture) {
        entity.setHeight(this.height);
        entity.setWeight(this.weight);
        entity.setMuscle(this.muscle);
        entity.setFat(this.fat);
        entity.setScore(this.score);
        entity.setBmi(this.bmi);
        entity.setFatRate(this.fatRate);
        entity.setPicture(picture);
        return entity;
    }
}