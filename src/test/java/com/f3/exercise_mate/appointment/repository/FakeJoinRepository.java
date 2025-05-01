package com.f3.exercise_mate.appointment.repository;

import com.f3.exercise_mate.appointment.application.interfaces.JoinRepository;
import com.f3.exercise_mate.appointment.domain.Join;
import com.f3.exercise_mate.appointment.domain.JoinStatus;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<Join> findByAppointmentIdAndStatus(Long appointmentId, JoinStatus status) {
        List<Join> list = store.values().stream().filter(join ->
                join.getAppointment().getId().equals(appointmentId) && join.getStatus().equals(status)
        ).toList();

        return list;
    }
}
