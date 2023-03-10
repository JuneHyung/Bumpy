package com.bump.bumpy.database.repository;

import com.bump.bumpy.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}