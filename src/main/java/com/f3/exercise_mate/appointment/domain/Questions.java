package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;

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
            throw new ExerciseMateException(ErrorCode.QUESTION_LIST_NULL);
        }

        this.questions = new ArrayList<>(questions);
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public void add(Question question) {
        if(question == null) {
            throw new ExerciseMateException(ErrorCode.QUESTION_NULL);
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
