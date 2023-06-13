package com.bump.bumpy.database.repository.data;

import com.bump.bumpy.database.entity.composite.DataHMealId;
import com.bump.bumpy.database.entity.data.DataHMeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DataHMealRepository extends JpaRepository<DataHMeal, DataHMealId> {
    List<DataHMeal> findByUserIdAndStdDateOrderBySeqAsc(String userId, Date stdDate);
    Optional<DataHMeal> findByStdDateAndUserIdAndSeq(Date stdDate, String userId, Integer seq);
    List<DataHMeal> findByStdDateAndUserIdOrderBySeqAsc(Date stdDate, String userId);

    List<DataHMeal> findByUserIdAndStdDateBetween(String userId, Date startDate, Date endDate);
}