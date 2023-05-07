package com.bump.bumpy.database.repository.data;

import com.bump.bumpy.database.entity.data.DataHWeight;
import com.bump.bumpy.database.entity.composite.DataHWeightId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataHWeightRepository extends JpaRepository<DataHWeight, DataHWeightId> {
}