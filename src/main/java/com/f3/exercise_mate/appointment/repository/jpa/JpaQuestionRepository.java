package com.f3.exercise_mate.appointment.repository.jpa;

import com.f3.exercise_mate.appointment.domain.Question;
import com.f3.exercise_mate.appointment.repository.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaQuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByAppointmentId(Long id);
}
