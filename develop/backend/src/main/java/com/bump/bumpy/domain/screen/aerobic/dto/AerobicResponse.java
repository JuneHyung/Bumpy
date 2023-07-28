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
import java.util.List;
import java.util.Map;

/**
 * A DTO for the {@link DataHAerobic} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AerobicResponse implements Serializable {
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
    private List<Map<String, String>> picture;

    // constructor from entity
    public AerobicResponse(DataHAerobic entity, List<Map<String, String>> picture) {
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
        this.picture = picture;
    }

}