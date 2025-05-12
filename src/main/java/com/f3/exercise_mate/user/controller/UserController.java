package com.f3.exercise_mate.user.controller;

import com.f3.exercise_mate.user.domain.User;
import com.f3.exercise_mate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("")
//    public User getUser() {
//        return userService.getUser();
//    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        user = userService.create(user);
        return ResponseEntity.ok(user);
    }

}
