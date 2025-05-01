package com.f3.exercise_mate.user.entity;

import com.f3.exercise_mate.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    private String username;

    public static UserEntity from(User user) {
        return new UserEntity(user.getId(), user.getAge(), user.getUsername());
    }

    public User toUser() {
        return new User(id, age, username);
    }

    public UserEntity(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}
