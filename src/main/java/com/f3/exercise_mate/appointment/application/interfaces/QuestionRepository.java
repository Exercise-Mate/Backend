package com.f3.exercise_mate.appointment.application.interfaces;

import com.f3.exercise_mate.appointment.application.dto.question.CreateQuestionRequestDto;
import com.f3.exercise_mate.appointment.domain.Question;

import java.util.List;

public interface QuestionRepository {
    Question save(Question question);
    Question findById(Long id);
    List<Question> saveAll(List<Question> questions);
}
