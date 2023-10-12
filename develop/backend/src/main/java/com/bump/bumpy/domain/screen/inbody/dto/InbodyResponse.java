package com.bump.bumpy.domain.screen.inbody.dto;

import com.bump.bumpy.database.entity.data.DataHInbody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * A DTO for the {@link DataHInbody} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InbodyResponse implements Serializable {
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
    private List<Map<String, String>> picture;

    // constructor from entity
    public InbodyResponse(DataHInbody entity, List<Map<String, String>> picture) {
        this.stdDate = entity.getStdDate();
        this.height = entity.getHeight();
        this.weight = entity.getWeight();
        this.muscle = entity.getMuscle();
        this.fat = entity.getFat();
        this.score = entity.getScore();
        this.bmi = entity.getBmi();
        this.fatRate = entity.getFatRate();
        this.picture = picture;
    }
}