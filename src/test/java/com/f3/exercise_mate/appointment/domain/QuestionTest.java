package com.f3.exercise_mate.appointment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    @DisplayName("약속 참여 질문이 정상적으로 작동된다.")
    void questionCreate_success() {
        // given
        String text = "this is question";

        // when
        Question question = new Question(1L, 1L, text);

        // then
        assertEquals(text, question.getQuestion());
    }

    @Test
    @DisplayName("질문의 길이가 최대 길이보다 크면 에러 발생")
    void questionLengthOver_questionCreate_throwError() {
        // given
        String text = "a".repeat(201);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, 1L, text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "갉", "슭", "숢", "됆"})
    @DisplayName("질문의 내용이 한글일 때도 최대 길이보다 길면 에러 발생")
    void questionLengthOverKorean_questionCreate_throwError(String korean) {
        // given
        String text = korean.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, 1L, text));
    }

    @Test
    @DisplayName("질문의 길이가 최소 길이보다 짧으면 에러발생")
    void questionLengthUnder_questionCreate_throwError() {
        // given
        String text = "a".repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, 1L, text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("질문의 값이 비어있으면 에러발생")
    void questionNullOrEmpty_questionCreate_throwError(String value) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, 1L, value));
    }

    @Test
    @DisplayName("질문의 수정 정삭 작동")
    void questionUpdate_success() {
        // given
        String text = "this is question";
        Question question = new Question(1L, 1L, text);

        // when
        String updatedText = "this is question updated";
        question.updateQuestion(updatedText);

        // then
        assertEquals(updatedText, question.getQuestion());
    }

    @Test
    @DisplayName("질문 수정 값의 길이가 최대 길이보다 크면 에러 발생")
    void questionLengthOver_questionUpdate_throwError() {
        // given
        String text = "this is question";
        Question question = new Question(1L, 1L, text);

        // when
        String updatedText = "a".repeat(201);

        // then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, 1L, updatedText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "갉", "슭", "숢", "됆"})
    @DisplayName("질문 수정 내용이 한글일 때도 최대 길이보다 길면 에러 발생")
    void questionLengthOverKorean_questionUpdate_throwError(String korean) {
        // given
        String text = "this is question";
        Question question = new Question(1L, 1L, text);

        // when
        String updatedText = korean.repeat(501);

        // then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, 1L, updatedText));
    }

    @Test
    @DisplayName("질문의 길이가 최소 길이보다 짧으면 에러발생")
    void questionLengthUnder_questionUpdate_throwError() {
        // given
        String text = "this is question";
        Question question = new Question(1L, 1L, text);

        // when
        String updatedText = "a".repeat(4);

        // then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, 1L, updatedText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("질문의 값이 비어있으면 에러발생")
    void questionNullOrEmpty_questionUpdate_throwError(String value) {
        // given
        String text = "this is question";
        Question question = new Question(1L, 1L, text);

        // when
        Question updatedQuestion = new Question(1L, 1L, text);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> question.updateQuestion(value));
    }
}