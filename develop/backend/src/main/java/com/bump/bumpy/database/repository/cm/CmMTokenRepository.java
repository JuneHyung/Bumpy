package com.bump.bumpy.database.repository.cm;

import com.bump.bumpy.database.entity.cm.CmMToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CmMTokenRepository extends JpaRepository<CmMToken, String> {
    long deleteByUserId(String subject);

    Optional<CmMToken> findByUserId(String subject);
}