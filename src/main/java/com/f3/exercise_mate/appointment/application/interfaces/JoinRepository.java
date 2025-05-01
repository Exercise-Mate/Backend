package com.f3.exercise_mate.appointment.application.interfaces;

import com.f3.exercise_mate.appointment.domain.Join;
import com.f3.exercise_mate.appointment.domain.JoinStatus;

import java.util.List;

public interface JoinRepository {
    Join save(Join join);
    Join findById(Long id);
    List<Join> findByAppointmentIdAndStatus(Long appointmentId, JoinStatus status);
}
