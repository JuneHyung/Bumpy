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
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.bump.bumpy.database.entity.data.DataHMeal} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DataHMealDto implements Serializable {
    @NotNull
    private Date stdDate;
    @NotNull
    private Integer seq;
    @Size(max = 100)
    @NotNull
    private String name;
    private Integer order;
    private Date time;
    private BigDecimal kcal;
    private BigDecimal water;
    @Size(max = 1000)
    private String memo;
    @Size(max = 1000)
    private String picture;
    private List<String> food;

    // to entity method for insert
    public DataHMeal toEntity() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DataHMeal entity = new DataHMeal();
        entity.setStdDate(this.stdDate);
        entity.setSeq(this.seq);
        entity.setName(this.name);
        entity.setOrder(this.order);
        entity.setTime(this.time);
        entity.setKcal(this.kcal);
        entity.setWater(this.water);
        entity.setMemo(this.memo);
        entity.setPicture(this.picture);
        entity.setFood(objectMapper.writeValueAsString(this.food));
        return entity;
    }

    // to entity method for update
    public DataHMeal updateEntity(DataHMeal entity) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        entity.setName(this.name);
        entity.setOrder(this.order);
        entity.setTime(this.time);
        entity.setKcal(this.kcal);
        entity.setWater(this.water);
        entity.setMemo(this.memo);
        entity.setPicture(this.picture);
        entity.setFood(objectMapper.writeValueAsString(this.food));
        return entity;
    }

    // to dto method
    public DataHMealDto(DataHMeal entity) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.stdDate = entity.getStdDate();
        this.seq = entity.getSeq();
        this.name = entity.getName();
        this.order = entity.getOrder();
        this.time = entity.getTime();
        this.kcal = entity.getKcal();
        this.water = entity.getWater();
        this.memo = entity.getMemo();
        this.picture = entity.getPicture();
        this.food = objectMapper.readValue(entity.getFood(), new TypeReference<List<String>>(){});
    }
}