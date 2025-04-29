package com.f3.exercise_mate.appointment.application.interfaces;

import com.f3.exercise_mate.appointment.domain.Join;

public interface JoinRepository {
    Join save(Join join);
    Join findById(Long id);
}
