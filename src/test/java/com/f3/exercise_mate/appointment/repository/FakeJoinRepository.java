package com.f3.exercise_mate.appointment.repository;

import com.f3.exercise_mate.appointment.application.interfaces.JoinRepository;
import com.f3.exercise_mate.appointment.domain.Join;

import java.util.HashMap;
import java.util.Map;

public class FakeJoinRepository implements JoinRepository {

    Map<Long, Join> store = new HashMap<>();

    @Override
    public Join save(Join join) {
        if (join.getId() != null) {
            store.put(join.getId(), join);
            return join;
        }
        Long id = store.size() + 1L;
        Join newJoin = new Join(id, join.getAppointment(), join.getUser(), join.getAnswers(), join.getStatus());
        store.put(id, newJoin);
        return newJoin;
    }

    @Override
    public Join findById(Long id) {
        return store.get(id);
    }
}
