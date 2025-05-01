package com.f3.exercise_mate.appointment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private Long id;
    private Question question;
    private String content;
}
