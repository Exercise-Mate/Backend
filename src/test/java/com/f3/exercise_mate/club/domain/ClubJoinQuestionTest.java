package com.f3.exercise_mate.club.domain;

import com.f3.exercise_mate.club.exception.ClubQuestionErrorCode;
import com.f3.exercise_mate.club.exception.ClubQuestionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ClubJoinQuestionTest {

    @DisplayName("질문이 5자 미만이면 예외가 발생한다.")
    @Test
    void validateQuestionTooShort() {
        // given
        String shortQuestion = "abc";

        // when & then
        assertThatThrownBy(() -> new ClubJoinQuestion(1L, 1L, shortQuestion))
                .isInstanceOf(ClubQuestionException.class)
                .hasMessageContaining(ClubQuestionErrorCode.CLUB_CREATE_JOIN_QUESTION_TOO_SHORT.getErrorMessage());
    }

    @DisplayName("질문이 200자 이상이면 예외가 발생한다.")
    @Test
    void validateQuestionTooLong() {
        // given
        String longQuestion = "a".repeat(201);

        // when & then
        assertThatThrownBy(() -> new ClubJoinQuestion(1L, 1L, longQuestion))
                .isInstanceOf(ClubQuestionException.class)
                .hasMessageContaining(ClubQuestionErrorCode.CLUB_CREATE_JOIN_QUESTION_TOO_LONG.getErrorMessage());
    }

    @DisplayName("질문이 유효하면 객체가 정상적으로 생성된다.")
    @Test
    void validateValidQuestion() {
        // given
        String validQuestion = "What is your favorite sport?";

        // when
        ClubJoinQuestion clubJoinQuestion = new ClubJoinQuestion(1L, 1L, validQuestion);

        // then
        assertThat(clubJoinQuestion.getQuestion()).isEqualTo(validQuestion);
    }
}
