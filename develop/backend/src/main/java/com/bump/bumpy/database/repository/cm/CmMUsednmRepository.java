package com.bump.bumpy.database.repository.cm;

import com.bump.bumpy.database.entity.cm.CmMUsednm;
import com.bump.bumpy.database.entity.composite.CmMUsednmId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmMUsednmRepository extends JpaRepository<CmMUsednm, CmMUsednmId> {
}