package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.appointment.application.exception.AppointmentErrorCode;
import com.f3.exercise_mate.appointment.application.exception.AppointmentException;
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
            throw new AppointmentException(AppointmentErrorCode.QUESTION_EMPTY);
        }

        if(content.length() < MIN_LENGTH) {
            throw new AppointmentException(AppointmentErrorCode.QUESTION_TOO_SHORT);
        }

        if(content.length() > MAX_LENGTH) {
            throw new AppointmentException(AppointmentErrorCode.QUESTION_TOO_LONG);
        }
    }



}
