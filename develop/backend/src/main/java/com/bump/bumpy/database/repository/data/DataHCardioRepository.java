package com.bump.bumpy.database.repository.data;

import com.bump.bumpy.database.entity.composite.DataHCardioId;
import com.bump.bumpy.database.entity.data.DataHCardio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DataHCardioRepository extends JpaRepository<DataHCardio, DataHCardioId> {
    Optional<DataHCardio> findByStdDateAndUserIdAndSeq(Date stdDate, String userId, Integer seq);
    List<DataHCardio> findByStdDateAndUserIdOrderBySeqAsc(Date stdDate, String userId);

    DataHCardio findFirstByUserIdOrderByStdDateDesc(String userId);

    List<DataHCardio> findByUserIdOrderByStdDateDesc(String userId);
}