package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private final User creator = new User(1L, 20, "john");
    private final Location location = new Location("서울시 강남구", "강남 풋살장", "");
    private final DateInfo dateInfo = new DateInfo(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1));
    private final Appointment appointment = Appointment.create(
            1L,
            "풋살 한판 뛰실분?",
            creator,
            "풋살 재밌게 찹시다.",
            Sport.FUTSAL,
            location,
            dateInfo,
            10);

    @Test
    @DisplayName("약속 참여 질문이 정상적으로 작동된다.")
    void questionCreate_success() {
        // given
        String text = "this is content";

        // when
        Question question = new Question(1L, appointment, text);

        // then
        assertEquals(text, question.getContent());
    }

    @Test
    @DisplayName("질문의 길이가 최대 길이보다 크면 에러 발생")
    void questionLengthOver_questionCreate_throwError() {
        // given
        String text = "a".repeat(201);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, appointment, text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "갉", "슭", "숢", "됆"})
    @DisplayName("질문의 내용이 한글일 때도 최대 길이보다 길면 에러 발생")
    void questionLengthOverKorean_questionCreate_throwError(String korean) {
        // given
        String text = korean.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, appointment, text));
    }

    @Test
    @DisplayName("질문의 길이가 최소 길이보다 짧으면 에러발생")
    void questionLengthUnder_questionCreate_throwError() {
        // given
        String text = "a".repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, appointment, text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("질문의 값이 비어있으면 에러발생")
    void questionNullOrEmpty_questionCreate_throwError(String value) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, appointment, value));
    }

    @Test
    @DisplayName("질문의 수정 정삭 작동")
    void questionUpdate_success() {
        // given
        String text = "this is content";
        Question question = new Question(1L, appointment, text);

        // when
        String updatedText = "this is content updated";
        question.updateQuestion(updatedText);

        // then
        assertEquals(updatedText, question.getContent());
    }

    @Test
    @DisplayName("질문 수정 값의 길이가 최대 길이보다 크면 에러 발생")
    void questionLengthOver_questionUpdate_throwError() {
        // given
        String text = "this is content";

        // when
        String updatedText = "a".repeat(201);

        // then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, appointment, updatedText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "갉", "슭", "숢", "됆"})
    @DisplayName("질문 수정 내용이 한글일 때도 최대 길이보다 길면 에러 발생")
    void questionLengthOverKorean_questionUpdate_throwError(String korean) {
        // given
        String text = "this is content";

        // when
        String updatedText = korean.repeat(501);

        // then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, appointment, updatedText));
    }

    @Test
    @DisplayName("질문의 길이가 최소 길이보다 짧으면 에러발생")
    void questionLengthUnder_questionUpdate_throwError() {
        // given
        String text = "this is content";

        // when
        String updatedText = "a".repeat(4);

        // then
        assertThrows(IllegalArgumentException.class, () -> new Question(1L, appointment, updatedText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("질문의 값이 비어있으면 에러발생")
    void questionNullOrEmpty_questionUpdate_throwError(String value) {
        // given
        String text = "this is content";
        Question question = new Question(1L, appointment, text);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> question.updateQuestion(value));
    }
}