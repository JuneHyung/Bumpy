package com.bump.bumpy.database.repository;

import com.bump.bumpy.database.entity.TokenStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenStoreRepository extends JpaRepository<TokenStore, String> {
    long deleteByUserId(String userId);
    Optional<TokenStore> findByUserId(String userId);
}