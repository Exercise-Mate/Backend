package com.f3.exercise_mate.appointment.repository;

import com.f3.exercise_mate.appointment.application.interfaces.ParticipantsRepository;
import com.f3.exercise_mate.appointment.domain.Participant;
import com.f3.exercise_mate.appointment.domain.Participants;
import com.f3.exercise_mate.appointment.repository.entity.ParticipantEntity;
import com.f3.exercise_mate.appointment.repository.jpa.JpaParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantsRepository {

    private final JpaParticipantRepository jpaParticipantRepository;

    @Override
    public Participants findByAppointmentId(Long appointmentId) {

        return null;
    }

    @Override
    public Participant save(Participant participant) {
        ParticipantEntity entity = ParticipantEntity.from(participant);
        entity = jpaParticipantRepository.save(entity);
        return entity.toParticipant();
    }
}
