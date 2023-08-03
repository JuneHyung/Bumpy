package com.bump.bumpy.domain.screen.aerobic.dto;

import com.bump.bumpy.database.entity.data.DataHAerobic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link DataHAerobic} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DataHAerobicDto implements Serializable {
    @NotNull
    private Date stdDate;
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
    @JsonIgnore
    private List<String> picture;


    // to entity method for insert
    public DataHAerobic toEntity(int seq, List<String> picture) {
        DataHAerobic entity = new DataHAerobic();
        entity.setStdDate(this.stdDate);
        entity.setSeq(seq);
        entity.setName(this.name);
        entity.setKcal(this.kcal);
        entity.setTime(this.time);
        entity.setInclineStart(this.inclineStart);
        entity.setInclineEnd(this.inclineEnd);
        entity.setSpeedStart(this.speedStart);
        entity.setSpeedEnd(this.speedEnd);
        entity.setMemo(this.memo);
        entity.setPicture(picture);
        return entity;
    }

    // to entity method for update
    public DataHAerobic updateEntity(DataHAerobic entity) {
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