package com.f3.exercise_mate.user.repository;

import com.f3.exercise_mate.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
