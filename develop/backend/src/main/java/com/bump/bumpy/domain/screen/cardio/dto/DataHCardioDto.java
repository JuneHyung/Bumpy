package com.bump.bumpy.domain.screen.cardio.dto;

import com.bump.bumpy.database.entity.data.DataHCardio;
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


    // to entity method for insert
    public DataHCardio toEntity() {
        DataHCardio entity = new DataHCardio();
        entity.setStdDate(this.stdDate);
        entity.setSeq(this.seq);
        entity.setName(this.name);
        entity.setKcal(this.kcal);
        entity.setTime(this.time);
        entity.setInclineStart(this.inclineStart);
        entity.setInclineEnd(this.inclineEnd);
        entity.setSpeedStart(this.speedStart);
        entity.setSpeedEnd(this.speedEnd);
        entity.setMemo(this.memo);
        entity.setPicture(this.picture);
        return entity;
    }

    // to entity method for update
    public DataHCardio updateEntity(DataHCardio entity) {
        entity.setName(this.name);
        entity.setKcal(this.kcal);
        entity.setTime(this.time);
        entity.setInclineStart(this.inclineStart);
        entity.setInclineEnd(this.inclineEnd);
        entity.setSpeedStart(this.speedStart);
        entity.setSpeedEnd(this.speedEnd);
        entity.setMemo(this.memo);
        entity.setPicture(this.picture);
        return entity;
    }
}