package com.f3.exercise_mate.appointment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelRangeTest {

    @Test
    @DisplayName("최소, 최대 레벨을 올바르게 설정할 시 생성이 성공한다.")
    void createLevelRange_success() {
        // given
        Level min = Level.BEGINNER;
        Level max = Level.BEGINNER;
        LevelRange levelRange = new LevelRange(min, max);

        // when, then
        assertEquals(min, levelRange.getMinLevel());
        assertEquals(max, levelRange.getMaxLevel());
    }

    @Test
    @DisplayName("최소 최대 레벨이 같은 경우 정상 생성")
    void createLevelRange_success2() {
        // given
        Level min = Level.BEGINNER;

        // when
        LevelRange levelRange = new LevelRange(min, min);

        // when,then
        assertEquals(min, levelRange.getMinLevel());
        assertEquals(min, levelRange.getMaxLevel());
    }

    @Test
    @DisplayName("최소 레벨의 입력이 최대 레벨보다 높다면 에러 발생")
    void createLevelRange_fail() {
        // given
        Level min = Level.NOVICE;
        Level max = Level.BEGINNER;

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new LevelRange(min, max));
    }

}