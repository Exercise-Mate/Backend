package com.f3.exercise_mate.appointment.repository.entity;

import com.f3.exercise_mate.appointment.domain.Question;
import com.f3.exercise_mate.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId; //

    private String content;


    public static QuestionEntity from(Question question) {
        return new QuestionEntity(question.getId(), question.getAppointment().getId(), question.getContent());
    }

    public Question toQuestion() {
        return new Question(id, content);
    }
}
