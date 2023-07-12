package com.bump.bumpy.database.repository.usr;

import com.bump.bumpy.database.entity.usr.UsrHEmailauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrHEmailauthRepository extends JpaRepository<UsrHEmailauth, String> {
    boolean existsByUserIdAndTokenAndEmail(String userId, String token, String email);
    long deleteByUserId(String userId);
    boolean existsByToken(String token);

    UsrHEmailauth findByUserIdAndTokenAndEmail(String userId, String verifyCode, String email);

    UsrHEmailauth findByUserIdAndEmail(String userId, String email);
}