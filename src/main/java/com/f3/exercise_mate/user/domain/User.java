package com.f3.exercise_mate.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private Long id;
    private int age;
    private String username;

    public User(Long id, int age, String username) {
        this.id = id;
        this.age = age;
        this.username = username;
    }
}
