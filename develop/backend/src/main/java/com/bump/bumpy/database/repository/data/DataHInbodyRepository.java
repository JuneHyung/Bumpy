package com.bump.bumpy.database.repository.data;

import com.bump.bumpy.database.entity.composite.DataHInbodyId;
import com.bump.bumpy.database.entity.data.DataHInbody;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataHInbodyRepository extends JpaRepository<DataHInbody, DataHInbodyId> {
}