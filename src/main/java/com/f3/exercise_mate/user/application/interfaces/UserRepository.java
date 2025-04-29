package com.f3.exercise_mate.user.application.interfaces;

import com.f3.exercise_mate.user.domain.User;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
}
