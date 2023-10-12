package com.bump.bumpy.domain.screen.weight.dto;

import com.bump.bumpy.database.entity.data.DataHWeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightResponse {
    @NotNull
    private Date stdDate;
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
    private List<Map<String, String>> picture;

    // constructor from Dto
    public WeightResponse(DataHWeightDto dto, List<Map<String, String>> picture) {
        this.stdDate = dto.getStdDate();
        this.seq = dto.getSeq();
        this.name = dto.getName();
        this.weightStart = dto.getWeightStart();
        this.weightEnd = dto.getWeightEnd();
        this.repsStart = dto.getRepsStart();
        this.repsEnd = dto.getRepsEnd();
        this.pollWeight = dto.getPollWeight();
        this.setReps = dto.getSetReps();
        this.measure = dto.getMeasure();
        this.memo = dto.getMemo();
        this.picture = picture;
    }

    public WeightResponse(DataHWeight entity, List<Map<String, String>> picture) {
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
        this.picture = picture;
    }
}
