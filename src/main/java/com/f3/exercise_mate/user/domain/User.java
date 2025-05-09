package com.f3.exercise_mate.user.domain;

import com.f3.exercise_mate.club.domain.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {
    private Long id;
    private int age;
    private String username;

    public User(Long id, int age, String username) {
        this.id = id;
        this.age = age;
        this.username = username;
    }

    public boolean isInRange(Club club) {
        return this.age >= club.getAgeLimitMin() && this.age <= club.getAgeLimitMax();
    }
}
