package com.bump.bumpy.database.repository.data;

import com.bump.bumpy.database.entity.composite.DataHCardioId;
import com.bump.bumpy.database.entity.data.DataHAerobic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DataHAerobicRepository extends JpaRepository<DataHAerobic, DataHCardioId> {
    DataHAerobic findFirstByUserIdAndNameOrderByKcalDescStdDateDesc(String userId, String name);
    List<DataHAerobic> findByStdDateBetweenAndUserIdOrderByStdDateAscSeqAsc(Date stdDateStart, Date stdDateEnd, String userId);
    Optional<DataHAerobic> findByStdDateAndUserIdAndSeq(Date stdDate, String userId, Integer seq);
    List<DataHAerobic> findByStdDateAndUserIdOrderBySeqAsc(Date stdDate, String userId);

    DataHAerobic findFirstByUserIdOrderByStdDateDesc(String userId);

    List<DataHAerobic> findByUserIdOrderByStdDateDesc(String userId);

    List<DataHAerobic> findTop10ByUserIdAndNameOrderByStdDateDesc(String userId, String name);

    List<DataHAerobic> findByUserIdAndStdDateBetweenOrderByStdDateAsc(String userId, Date time, Date time1);
}