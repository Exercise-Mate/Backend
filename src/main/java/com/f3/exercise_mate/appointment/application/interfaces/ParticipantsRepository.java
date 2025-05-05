package com.f3.exercise_mate.appointment.application.interfaces;

import com.f3.exercise_mate.appointment.domain.Participant;
import com.f3.exercise_mate.appointment.domain.Participants;
import com.f3.exercise_mate.appointment.repository.entity.ParticipantEntity;

import java.util.List;

public interface ParticipantsRepository {
    Participants findByAppointmentId(Long appointmentId);

    Participant save(Participant participant);
}
