package com.f3.exercise_mate.club.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class ClubJoinQuestionListTest {

    @DisplayName("ClubJoinQuestionList에 null이 들어오면 빈 리스트가 반환된다.")
    @Test
    void validateNullList() {
        // given
        List<ClubJoinQuestion> nullList = null;

        // when
        ClubJoinQuestionList clubJoinQuestionList = new ClubJoinQuestionList(nullList);

        // then
        assertThat(clubJoinQuestionList.getClubJoinQuestionsList()).isEmpty();
    }

    @DisplayName("ClubJoinQuestionList에 빈 리스트가 들어오면 빈 리스트가 반환된다.")
    @Test
    void validateEmptyList() {
        // given
        List<ClubJoinQuestion> emptyList = Arrays.asList();

        // when
        ClubJoinQuestionList clubJoinQuestionList = new ClubJoinQuestionList(emptyList);

        // then
        assertThat(clubJoinQuestionList.getClubJoinQuestionsList()).isEmpty();
    }

    @DisplayName("ClubJoinQuestionList에 유효한 ClubJoinQuestion이 있으면 리스트에 정상적으로 추가된다.")
    @Test
    void validateValidQuestions() {
        // given
        ClubJoinQuestion validQuestion = new ClubJoinQuestion(1L, 1L, "What is your favorite sport?");
        List<ClubJoinQuestion> questionsList = Arrays.asList(validQuestion);

        // when
        ClubJoinQuestionList clubJoinQuestionList = new ClubJoinQuestionList(questionsList);

        // then
        assertThat(clubJoinQuestionList.getClubJoinQuestionsList()).hasSize(1);
        assertThat(clubJoinQuestionList.getClubJoinQuestionsList().get(0).getQuestion()).isEqualTo("What is your favorite sport?");
    }
}
