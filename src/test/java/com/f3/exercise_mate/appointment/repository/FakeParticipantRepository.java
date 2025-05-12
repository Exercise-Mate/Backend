package com.f3.exercise_mate.appointment.repository;

import com.f3.exercise_mate.appointment.application.interfaces.ParticipantsRepository;
import com.f3.exercise_mate.appointment.domain.Participant;
import com.f3.exercise_mate.appointment.domain.Participants;

public class FakeParticipantRepository implements ParticipantsRepository {
    @Override
    public Participants findByAppointmentId(Long appointmentId) {
        return null;
    }

    @Override
    public Participant save(Participant participant) {
        return null;
    }
}
