package com.f3.exercise_mate.appointment.repository;

import com.f3.exercise_mate.appointment.application.interfaces.QuestionRepository;
import com.f3.exercise_mate.appointment.domain.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FakeQuestionRepository implements QuestionRepository {

    Map<Long, Question> store = new HashMap<>();

    @Override
    public Question save(Question question) {
        if(question.getId() != null) {
            store.put(question.getId(), question);
            return question;
        }
        Long id = store.size() + 1L;
        Question newQuestion = new Question(id, question.getAppointment(), question.getContent());
        store.put(id, newQuestion);
        return newQuestion;
    }

    @Override
    public Question findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Question> saveAll(List<Question> questions) {
        List<Question> newQuestions = new ArrayList<>();
        for(Question question : questions) {
            newQuestions.add(save(question));
        }
        return newQuestions;
    }

    public List<Question> findByAppointmentId(Long appointmentId) {
        return store.values().stream()
                .filter(q -> q.getAppointment().getId().equals(appointmentId))
                .collect(Collectors.toList());
    }
}
