package com.f3.exercise_mate.appointment.application.interfaces;

import com.f3.exercise_mate.appointment.domain.Appointment;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    Appointment findById(Long id);
}
