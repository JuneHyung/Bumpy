package com.bump.bumpy.database.repository.usr;

import com.bump.bumpy.database.entity.usr.UsrMUsr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrMUsrRepository extends JpaRepository<UsrMUsr, String> {
    boolean existsByUserId(String userId);
}