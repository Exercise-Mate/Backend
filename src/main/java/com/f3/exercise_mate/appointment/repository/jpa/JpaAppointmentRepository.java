package com.f3.exercise_mate.appointment.repository.jpa;

import com.f3.exercise_mate.appointment.repository.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}
