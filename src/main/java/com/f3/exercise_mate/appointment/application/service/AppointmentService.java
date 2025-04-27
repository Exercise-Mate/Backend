package com.f3.exercise_mate.appointment.application.service;

import com.f3.exercise_mate.appointment.application.dto.CreateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.UpdateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.interfaces.AppointmentRepository;
import com.f3.exercise_mate.appointment.domain.Appointment;
import com.f3.exercise_mate.user.entity.User;
import com.f3.exercise_mate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final UserService userService;

    public Appointment createAppointment(CreateAppointmentRequestDto dto) {
        User user = userService.getUser(dto.creatorId());
        Appointment appointment = Appointment.create(null, dto.title(), user, dto.description(), dto.sport(), dto.location(), dto.dateInfo());

        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment updateAppointment(Long appointmentId, UpdateAppointmentRequestDto dto) {
        Appointment appointment = appointmentRepository.findById(appointmentId);
        User user = userService.getUser(dto.creatorId());

        appointment.updateAppointment(user, dto);
        return appointmentRepository.save(appointment);
    }
}
