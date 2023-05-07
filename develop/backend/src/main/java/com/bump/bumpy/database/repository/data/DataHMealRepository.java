package com.bump.bumpy.database.repository.data;

import com.bump.bumpy.database.entity.data.DataHMeal;
import com.bump.bumpy.database.entity.composite.DataHMealId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataHMealRepository extends JpaRepository<DataHMeal, DataHMealId> {
}