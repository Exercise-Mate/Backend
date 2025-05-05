package com.f3.exercise_mate.appointment.repository;

import com.f3.exercise_mate.appointment.application.interfaces.QuestionRepository;
import com.f3.exercise_mate.appointment.domain.Question;
import com.f3.exercise_mate.appointment.repository.entity.AppointmentEntity;
import com.f3.exercise_mate.appointment.repository.entity.QuestionEntity;
import com.f3.exercise_mate.appointment.repository.jpa.JpaQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {

    private final JpaQuestionRepository jpaQuestionRepository;

    @Override
    public Question save(Question question) {
        QuestionEntity entity = jpaQuestionRepository.save(QuestionEntity.from(question));
        return entity.toQuestion();
    }

    @Override
    public Question findById(Long id) {
        QuestionEntity entity = jpaQuestionRepository.findById(id).orElseThrow();
        return entity.toQuestion();
    }

    @Override
    public List<Question> saveAll(List<Question> questions) {
        List<QuestionEntity> entities = questions.stream().map(q -> new QuestionEntity(q.getId(), q.getAppointment().getId(), q.getContent())).toList();
        entities = jpaQuestionRepository.saveAll(entities);
        List<Question> list = entities.stream().map(QuestionEntity::toQuestion).toList();
        return list;
    }

    public List<Question> findByAppointmentId(Long id) {
        List<QuestionEntity> questions = jpaQuestionRepository.findByAppointmentId(id);
        return questions.stream().map(QuestionEntity::toQuestion).toList();
    }

    public void delete(Question question) {
        jpaQuestionRepository.delete(QuestionEntity.from(question));
    }
}
