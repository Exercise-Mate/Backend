package com.f3.exercise_mate.user.controller;

import com.f3.exercise_mate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping
//    public User getUser() {
//        return userService.getUser();
//    }

}
