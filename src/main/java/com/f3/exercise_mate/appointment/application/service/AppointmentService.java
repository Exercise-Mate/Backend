package com.f3.exercise_mate.appointment.application.service;

import com.f3.exercise_mate.appointment.application.dto.CreateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.JoinAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.UpdateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.interfaces.AppointmentRepository;
import com.f3.exercise_mate.appointment.application.interfaces.QuestionRepository;
import com.f3.exercise_mate.appointment.domain.Appointment;
import com.f3.exercise_mate.appointment.domain.Question;
import com.f3.exercise_mate.user.domain.User;
import com.f3.exercise_mate.user.service.UserService;


public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final QuestionRepository questionRepository;

    private final UserService userService;

    public AppointmentService(AppointmentRepository appointmentRepository, QuestionRepository questionRepository, UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.questionRepository = questionRepository;
        this.userService = userService;
    }

    public Appointment createAppointment(CreateAppointmentRequestDto dto) {
        User user = userService.getUser(dto.creatorId());
        Appointment appointment = Appointment.create(null, dto.title(), user, dto.description(), dto.sport(), dto.location(), dto.dateInfo(), dto.maxParticipant());
        Appointment savedAppointment = appointmentRepository.save(appointment);

        dto.questions().forEach(request -> questionRepository.save(new Question(null, savedAppointment, request.content())));

        return savedAppointment;
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

    public void joinAppointment(JoinAppointmentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Appointment appointment = appointmentRepository.findById(dto.appointmentId());

        appointment.join(user);
    }
}
