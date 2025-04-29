package com.f3.exercise_mate.appointment.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions {
    private final List<Question> questions;

    public Questions() {
        questions = new ArrayList<>();
    }

    public Questions(List<Question> questions) {
        if (questions == null || questions.isEmpty()) {
            this.questions = new ArrayList<>();
            return;
        }

        if (questions.contains(null)) {
            throw new IllegalArgumentException("질문 리스트에 null이 포함 될 수 없습니다.");
        }

        this.questions = new ArrayList<>(questions);
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public void add(Question question) {
        if(question == null) {
            throw new IllegalArgumentException("질문은 null일 수 없습니다.");
        }

        questions.add(question);
    }

    public void remove(Question question) {
        questions.remove(question);
    }

    public int size() {
        return questions.size();
    }
}
