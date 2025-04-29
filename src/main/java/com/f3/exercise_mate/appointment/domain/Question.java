package com.f3.exercise_mate.appointment.domain;

import lombok.Getter;

@Getter
public class Question {
    private Long id;
    private Appointment appointment;
    private String content;

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 200;

    public Question(String content) {
        this.content = content;
    }

    public Question(Long id, Appointment appointment, String content) {
        checkQuestion(content);
        this.id = id;
        this.appointment = appointment;
        this.content = content;
    }

    public void updateQuestion(String updatedQuestion) {
        checkQuestion(updatedQuestion);
        this.content = updatedQuestion;
    }

    private void checkQuestion(String content) {
        if(content == null || content.isEmpty()) {
            throw new IllegalArgumentException("질문은 비어 있으면 안됩니다.");
        }

        if(content.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("질문의 최소 길이는 5입니다.");
        }

        if(content.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("질문의 최대 길이는 200입니다.");
        }
    }



}
