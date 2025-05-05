package com.f3.exercise_mate.appointment.repository;

import com.f3.exercise_mate.appointment.application.interfaces.AppointmentRepository;
import com.f3.exercise_mate.appointment.domain.Appointment;
import com.f3.exercise_mate.appointment.repository.entity.AppointmentEntity;
import com.f3.exercise_mate.appointment.repository.jpa.JpaAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final JpaAppointmentRepository jpaAppointmentRepository;

    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity entity = AppointmentEntity.from(appointment);
        entity = jpaAppointmentRepository.save(entity);
        return entity.toAppointment();
    }

    @Override
    public Appointment findById(Long id) {
        AppointmentEntity entity = jpaAppointmentRepository.findById(id).orElseThrow();
        return entity.toAppointment();
    }
}
