package com.bump.bumpy.domain.screen.meal.dto;

import com.bump.bumpy.database.entity.data.DataHMeal;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Size(max = 20)
    @NotNull
    private String userId;
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
    public DataHMeal toEntity() {
        DataHMeal entity = new DataHMeal();
        entity.setStdDate(this.stdDate);
        entity.setUserId(this.userId);
        entity.setSeq(this.seq);
        entity.setName(this.name);
        entity.setOrder(this.order);
        entity.setTime(this.time);
        entity.setKcal(this.kcal);
        entity.setWater(this.water);
        entity.setMemo(this.memo);
        entity.setPicture(this.picture);
        switch (food.size()) {
            case 10:
                entity.setFood10(this.food.get(9));
            case 9:
                entity.setFood9(this.food.get(8));
            case 8:
                entity.setFood8(this.food.get(7));
            case 7:
                entity.setFood7(this.food.get(6));
            case 6:
                entity.setFood6(this.food.get(5));
            case 5:
                entity.setFood5(this.food.get(4));
            case 4:
                entity.setFood4(this.food.get(3));
            case 3:
                entity.setFood3(this.food.get(2));
            case 2:
                entity.setFood2(this.food.get(1));
            case 1:
                entity.setFood1(this.food.get(0));
            default:
                break;
        }
        return entity;
    }

    // to entity method for update
    public DataHMeal updateEntity(DataHMeal entity) {
        entity.setName(this.name);
        entity.setOrder(this.order);
        entity.setTime(this.time);
        entity.setKcal(this.kcal);
        entity.setWater(this.water);
        entity.setMemo(this.memo);
        entity.setPicture(this.picture);
        entity.setFood1(null);
        entity.setFood2(null);
        entity.setFood3(null);
        entity.setFood4(null);
        entity.setFood5(null);
        entity.setFood6(null);
        entity.setFood7(null);
        entity.setFood8(null);
        entity.setFood9(null);
        entity.setFood10(null);
        switch (this.food.size()) {
            case 10:
                entity.setFood10(this.food.get(9));
            case 9:
                entity.setFood9(this.food.get(8));
            case 8:
                entity.setFood8(this.food.get(7));
            case 7:
                entity.setFood7(this.food.get(6));
            case 6:
                entity.setFood6(this.food.get(5));
            case 5:
                entity.setFood5(this.food.get(4));
            case 4:
                entity.setFood4(this.food.get(3));
            case 3:
                entity.setFood3(this.food.get(2));
            case 2:
                entity.setFood2(this.food.get(1));
            case 1:
                entity.setFood1(this.food.get(0));
            default:
                break;
        }
        return entity;
    }

    // to dto method
    public DataHMealDto(DataHMeal entity) {
        this.stdDate = entity.getStdDate();
        this.userId = entity.getUserId();
        this.seq = entity.getSeq();
        this.name = entity.getName();
        this.order = entity.getOrder();
        this.time = entity.getTime();
        this.kcal = entity.getKcal();
        this.water = entity.getWater();
        this.memo = entity.getMemo();
        this.picture = entity.getPicture();
        this.food = new ArrayList<>();

        if(entity.getFood1() != null) {
            this.food.add(entity.getFood1());
        }
        if(entity.getFood2() != null) {
            this.food.add(entity.getFood2());
        }
        if(entity.getFood3() != null) {
            this.food.add(entity.getFood3());
        }
        if(entity.getFood4() != null) {
            this.food.add(entity.getFood4());
        }
        if(entity.getFood5() != null) {
            this.food.add(entity.getFood5());
        }
        if(entity.getFood6() != null) {
            this.food.add(entity.getFood6());
        }
        if(entity.getFood7() != null) {
            this.food.add(entity.getFood7());
        }
        if(entity.getFood8() != null) {
            this.food.add(entity.getFood8());
        }
        if(entity.getFood9() != null) {
            this.food.add(entity.getFood9());
        }
        if(entity.getFood10() != null) {
            this.food.add(entity.getFood10());
        }
    }
}