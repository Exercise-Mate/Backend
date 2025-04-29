package com.f3.exercise_mate.appointment.application.dto;

import com.f3.exercise_mate.appointment.domain.DateInfo;
import com.f3.exercise_mate.appointment.domain.Location;
import com.f3.exercise_mate.appointment.domain.Question;
import com.f3.exercise_mate.appointment.domain.Sport;

import java.util.List;

public record UpdateAppointmentRequestDto(
        String title,
        Long creatorId,
        String description,
        Sport sport,
        Location location,
        DateInfo dateInfo,
        List<Question> questions) {
}
