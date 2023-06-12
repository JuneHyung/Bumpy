package com.bump.bumpy.database.repository.data;

import com.bump.bumpy.database.entity.composite.DataHInbodyId;
import com.bump.bumpy.database.entity.data.DataHInbody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface DataHInbodyRepository extends JpaRepository<DataHInbody, DataHInbodyId> {
    Optional<DataHInbody> findByStdDateAndUserId(Date stdDate, String userId);
}