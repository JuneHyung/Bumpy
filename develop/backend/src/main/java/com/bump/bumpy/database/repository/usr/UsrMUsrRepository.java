package com.bump.bumpy.database.repository.usr;

import com.bump.bumpy.database.entity.usr.UsrMUsr;
import com.bump.bumpy.domain.user.dto.UserSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsrMUsrRepository extends JpaRepository<UsrMUsr, String> {
    UsrMUsr findByUserId(String userId);
    boolean existsByUserId(String userId);
}