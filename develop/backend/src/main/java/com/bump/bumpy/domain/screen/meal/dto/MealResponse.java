package com.bump.bumpy.domain.screen.meal.dto;

import com.bump.bumpy.database.entity.data.DataHMeal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * A DTO for the {@link DataHMeal} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MealResponse implements Serializable {
    @NotNull
    private Date stdDate;
    private Integer seq;
    @Size(max = 100)
    @NotNull
    private String name;
    private LocalTime time;
    private BigDecimal kcal;
    private BigDecimal water;
    @Size(max = 1000)
    private String memo;
    private List<String> food;
    private List<Map<String, String>> picture;

    // constructor from entity
    public MealResponse(DataHMeal entity, List<Map<String, String>> picture) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.stdDate = entity.getStdDate();
        this.seq = entity.getSeq();
        this.name = entity.getName();
        this.time = entity.getTime();
        this.kcal = entity.getKcal();
        this.water = entity.getWater();
        this.memo = entity.getMemo();
        this.food = objectMapper.readValue(entity.getFood(), new TypeReference<List<String>>(){});
        this.picture = picture;
    }
}