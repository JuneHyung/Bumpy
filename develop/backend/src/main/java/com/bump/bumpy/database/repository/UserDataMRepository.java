package com.bump.bumpy.database.repository;

import com.bump.bumpy.database.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataMRepository extends JpaRepository<UserData, Integer> {
}