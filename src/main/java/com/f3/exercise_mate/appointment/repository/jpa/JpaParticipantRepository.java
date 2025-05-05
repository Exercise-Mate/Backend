package com.f3.exercise_mate.appointment.repository.jpa;

import com.f3.exercise_mate.appointment.repository.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
