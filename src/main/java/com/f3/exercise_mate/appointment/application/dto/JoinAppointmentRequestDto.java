package com.f3.exercise_mate.appointment.application.dto;

import com.f3.exercise_mate.appointment.application.dto.question.AnswerRequestDto;

import java.util.List;

public record JoinAppointmentRequestDto(Long appointmentId, Long userId, List<AnswerRequestDto> answers) {
}
