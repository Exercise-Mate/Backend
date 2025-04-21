package com.f3.exercise_mate.user.service;

import com.f3.exercise_mate.user.entity.User;
import com.f3.exercise_mate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser() {
        User user = userRepository.save(new User("test"));
        return userRepository.findById(user.getId()).orElseThrow();
    }
}
