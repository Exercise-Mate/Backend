package com.f3.exercise_mate.appointment.domain;

import lombok.Getter;

@Getter
public class Question {
    private Long id;
    private Long appointmentId;
    private String question;

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 200;

    public Question(Long id, Long appointmentId, String question) {
        checkQuestion(question);
        this.id = id;
        this.appointmentId = appointmentId;
        this.question = question;
    }

    public void updateQuestion(String updatedQuestion) {
        checkQuestion(updatedQuestion);
        this.question = updatedQuestion;
    }

    private void checkQuestion(String question) {
        if(question == null || question.length() == 0) {
            throw new IllegalArgumentException("질문은 비어 있으면 안됩니다.");
        }

        if(question.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("질문의 최소 길이는 5입니다.");
        }

        if(question.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("질문의 최대 길이는 200입니다.");
        }
    }



}
