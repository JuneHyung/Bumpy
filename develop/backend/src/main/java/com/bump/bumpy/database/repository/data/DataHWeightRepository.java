package com.bump.bumpy.database.repository.data;

import com.bump.bumpy.database.entity.composite.DataHWeightId;
import com.bump.bumpy.database.entity.data.DataHWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DataHWeightRepository extends JpaRepository<DataHWeight, DataHWeightId> {
    List<DataHWeight> findByStdDateAndUserIdOrderBySeqAsc(Date stdDate, String userId);

    Optional<DataHWeight> findByStdDateAndUserIdAndSeq(Date stdDate, String userId, Integer seq);

    DataHWeight findFirstByUserIdOrderByStdDateDesc(String userId);

    List<DataHWeight> findByUserIdOrderByStdDateDesc(String userId);
}
