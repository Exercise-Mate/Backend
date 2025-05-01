package com.f3.exercise_mate.user.service;

import com.f3.exercise_mate.user.application.interfaces.UserRepository;
import com.f3.exercise_mate.user.domain.User;
import com.f3.exercise_mate.user.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    public User getUser() {
//        User user = userRepository.save(new User("test"));
//        return userRepository.findById(user.getId()).orElseThrow();
//    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }
}
