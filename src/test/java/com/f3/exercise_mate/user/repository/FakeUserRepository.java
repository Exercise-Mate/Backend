package com.f3.exercise_mate.user.repository;

import com.f3.exercise_mate.user.application.interfaces.UserRepository;
import com.f3.exercise_mate.user.domain.User;

import java.util.HashMap;
import java.util.Map;

public class FakeUserRepository implements UserRepository {

    private static Map<Long, User> store = new HashMap<>();

    @Override
    public User save(User user) {
        if(user.getId() != null) {
            store.put(user.getId(), user);
            return user;
        }
        Long id = store.size() + 1L;
        store.put(id, user);
        return store.get(id);
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }
}
