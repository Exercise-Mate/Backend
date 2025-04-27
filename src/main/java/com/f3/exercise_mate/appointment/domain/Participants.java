package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.user.entity.User;
import org.hibernate.query.criteria.JpaDerivedRoot;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<User> users;
    private final int MAX_PARTICIPANTS;

    public Participants() {
        this.users = new ArrayList<>();
        MAX_PARTICIPANTS = 20;
    }

    public static Participants defaultCreate(List<User> users) {
        return new Participants(users, 20);
    }

    public Participants(List<User> users, int maxParticipants) {
        this.users = users == null ? new ArrayList<>() : new ArrayList<>(users);
        this.MAX_PARTICIPANTS = maxParticipants;
    }

    public void add(User user) {
        if(users.size() >= MAX_PARTICIPANTS) {
            throw new IllegalArgumentException("최대 참여 상태입니다.");
        }

        if(user == null) {
            throw new IllegalArgumentException("유저는 null일 수 없습니다.");
        }

        if(users.contains(user)) {
            throw new IllegalArgumentException("이미 참가하고 있는 user입니다");
        }

        users.add(user);
    }

    public void remove(User user) {
        if(user == null) {
            throw new IllegalArgumentException("유저는 null일 수 없습니다.");
        }

        if(!users.contains(user)) {
            throw new IllegalArgumentException("참여자 목록에 없는 user입니다.");
        }

        users.remove(user);
    }

    public int size() {
        return users.size();
    }

}
