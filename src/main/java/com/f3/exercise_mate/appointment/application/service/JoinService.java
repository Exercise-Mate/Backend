package com.f3.exercise_mate.appointment.application.service;

import com.f3.exercise_mate.appointment.application.dto.JoinAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.exception.AppointmentErrorCode;
import com.f3.exercise_mate.appointment.application.exception.AppointmentException;
import com.f3.exercise_mate.appointment.application.interfaces.AppointmentRepository;
import com.f3.exercise_mate.appointment.application.interfaces.JoinRepository;
import com.f3.exercise_mate.appointment.application.interfaces.QuestionRepository;
import com.f3.exercise_mate.appointment.domain.Answer;
import com.f3.exercise_mate.appointment.domain.Appointment;
import com.f3.exercise_mate.appointment.domain.Join;
import com.f3.exercise_mate.appointment.domain.JoinStatus;
import com.f3.exercise_mate.user.domain.User;
import com.f3.exercise_mate.user.service.UserService;

import java.util.List;

public class JoinService {

    private final UserService userService;
    private final AppointmentService appointmentService;
    private final QuestionRepository questionRepository;
    private final JoinRepository joinRepository;
    private final AppointmentRepository appointmentRepository;

    public JoinService(UserService userService, AppointmentService appointmentService, QuestionRepository questionRepository, JoinRepository joinRepository, AppointmentRepository appointmentRepository) {
        this.userService = userService;
        this.appointmentService = appointmentService;
        this.questionRepository = questionRepository;
        this.joinRepository = joinRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Join requestJoin(JoinAppointmentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Appointment appointment = appointmentService.getAppointment(dto.appointmentId());

        if(!appointment.isAvailableAge(user.getAge())) {
            throw new AppointmentException(AppointmentErrorCode.AGE_RANGE_NOT_ACCEPTED);
        }

        List<Answer> answers = dto.answers().stream()
                .map(answer -> new Answer(null, questionRepository.findById(answer.questionId()), answer.content()))
                .toList();

        Join join = new Join(appointment, user, answers);

        return joinRepository.save(join);
    }

    public List<Join> getJoins(Long appointmentId, JoinStatus joinStatus) {
        return joinRepository.findByAppointmentIdAndStatus(appointmentId, joinStatus);
    }

    public void approveJoin(Long joinId) {
        Join join = joinRepository.findById(joinId);
        join.accepted();

        Appointment appointment = join.getAppointment();
        appointment.join(join.getUser());

        appointmentRepository.save(appointment);
        joinRepository.save(join);
    }

    public void rejectJoin(Long joinId) {
        Join join = joinRepository.findById(joinId);
        join.rejected();

        joinRepository.save(join);
    }

}
