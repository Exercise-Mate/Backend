package com.f3.exercise_mate.appointment.application.service;

import com.f3.exercise_mate.appointment.application.dto.JoinAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.interfaces.JoinRepository;
import com.f3.exercise_mate.appointment.application.interfaces.QuestionRepository;
import com.f3.exercise_mate.appointment.domain.Answer;
import com.f3.exercise_mate.appointment.domain.Appointment;
import com.f3.exercise_mate.appointment.domain.Join;
import com.f3.exercise_mate.user.domain.User;
import com.f3.exercise_mate.user.service.UserService;

import java.util.List;

public class JoinService {

    private final UserService userService;
    private final AppointmentService appointmentService;
    private final QuestionRepository questionRepository;
    private final JoinRepository joinRepository;

    public JoinService(UserService userService, AppointmentService appointmentService, QuestionRepository questionRepository, JoinRepository joinRepository) {
        this.userService = userService;
        this.appointmentService = appointmentService;
        this.questionRepository = questionRepository;
        this.joinRepository = joinRepository;
    }


    public Join requestJoin(JoinAppointmentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Appointment appointment = appointmentService.getAppointment(dto.appointmentId());

        if(!appointment.isAvailableAge(user.getAge())) {
            throw new IllegalArgumentException("연령 제한으로 참여할 수 없습니다.");
        }

        List<Answer> answers = dto.answers().stream()
                .map(answer -> new Answer(null, questionRepository.findById(answer.questionId()), answer.content()))
                .toList();

        Join join = new Join(appointment, user, answers);

        return joinRepository.save(join);
    }


}
