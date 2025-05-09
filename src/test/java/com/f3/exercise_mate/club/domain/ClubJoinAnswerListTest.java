package com.f3.exercise_mate.club.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ClubJoinAnswerListTest {

    @DisplayName("ClubJoinAnswerList에 null이 들어오면 빈 리스트가 반환된다.")
    @Test
    void validateNullList() {
        // given
        ClubJoinAnswerList clubJoinAnswerList = new ClubJoinAnswerList(null);

        // when
        var result = clubJoinAnswerList.getClubJoinAnswersList();

        // then
        assertTrue(result.isEmpty());
    }

    @DisplayName("ClubJoinAnswerList에 빈 리스트가 들어오면 빈 리스트가 반환된다.")
    @Test
    void validateEmptyList() {
        // given
        ClubJoinAnswerList clubJoinAnswerList = new ClubJoinAnswerList(Arrays.asList());

        // when
        var result = clubJoinAnswerList.getClubJoinAnswersList();

        // then
        assertTrue(result.isEmpty());
    }

    @DisplayName("ClubJoinAnswerList에 유효한 ClubJoinAnswer가 들어오면 정상적으로 반환된다.")
    @Test
    void validateValidList() {
        // given
        ClubJoinAnswer validAnswer = new ClubJoinAnswer(1L, 1L, 1L, "열심히 하겠습니다.");
        ClubJoinAnswerList clubJoinAnswerList = new ClubJoinAnswerList(Arrays.asList(validAnswer));

        // when
        var result = clubJoinAnswerList.getClubJoinAnswersList();

        // then
        assertEquals(1, result.size());
        assertEquals("열심히 하겠습니다.", result.get(0).getAnswer());
    }
}
