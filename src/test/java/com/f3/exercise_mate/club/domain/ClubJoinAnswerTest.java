package com.f3.exercise_mate.club.domain;

import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ClubJoinAnswerTest {

    @DisplayName("답변이 5자 미만이면 예외가 발생한다.")
    @Test
    void validateAnswerTooShort() {
        // given
        String shortAnswer = "abc";

        // when & then
        assertThatThrownBy(() -> new ClubJoinAnswer(1L, 1L, 1L, shortAnswer).validate())
                .isInstanceOf(ExerciseMateException.class)
                .hasMessageContaining(ErrorCode.CLUB_JOIN_ANSWER_TOO_SHORT.getErrorMessage());
    }

    @DisplayName("답변이 200자 초과이면 예외가 발생한다.")
    @Test
    void validateAnswerTooLong() {
        // given
        String longAnswer = "a".repeat(201);

        // when & then
        assertThatThrownBy(() -> new ClubJoinAnswer(1L, 1L, 1L, longAnswer).validate())
                .isInstanceOf(ExerciseMateException.class)
                .hasMessageContaining(ErrorCode.CLUB_JOIN_ANSWER_TOO_LONG.getErrorMessage());
    }

    @DisplayName("답변이 비어 있으면 예외가 발생한다.")
    @Test
    void validateAnswerEmpty() {
        // given
        String emptyAnswer = null;

        // when & then
        assertThatThrownBy(() -> new ClubJoinAnswer(1L, 1L, 1L, emptyAnswer).validate())
                .isInstanceOf(ExerciseMateException.class)
                .hasMessageContaining(ErrorCode.CLUB_JOIN_ANSWER_REQUIRED.getErrorMessage());
    }

    @DisplayName("유효한 답변이면 예외가 발생하지 않는다.")
    @Test
    void validateValidAnswer() {
        // given
        String validAnswer = "열심히 하겠습니다.";

        // when
        ClubJoinAnswer clubJoinAnswer = new ClubJoinAnswer(1L, 1L, 1L, validAnswer);
        clubJoinAnswer.validate();

        // then
        assertEquals(validAnswer, clubJoinAnswer.getAnswer());
    }
}
