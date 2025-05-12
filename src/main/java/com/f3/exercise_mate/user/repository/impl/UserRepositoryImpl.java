package com.f3.exercise_mate.user.repository.impl;

import com.f3.exercise_mate.user.application.interfaces.UserRepository;
import com.f3.exercise_mate.user.domain.User;
import com.f3.exercise_mate.user.entity.UserEntity;
import com.f3.exercise_mate.user.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    
    private final JpaUserRepository jpaUserRepository;
    
    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.from(user);
        UserEntity save = jpaUserRepository.save(userEntity);

        return save.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = jpaUserRepository.findById(id).orElseThrow();
        return userEntity.toUser();
    }
}
