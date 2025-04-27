package com.f3.exercise_mate.appointment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgeRangeTest {

    @Test
    @DisplayName("나이가 정상적으로 입력시 객체가 생성된다.")
    void successTest() {
        // given
        int min = 20;
        int max = 40;

        // when
        AgeRange range = new AgeRange(min, max);
        AgeRange unrestricted = AgeRange.unrestricted();

        // then
        assertEquals(min, range.getMinAge());
        assertEquals(max, range.getMaxAge());
        assertNull(unrestricted.getMinAge());
        assertNull(unrestricted.getMaxAge());
    }

    @Test
    @DisplayName("최소 나이가 최대 나이보다 많으면 에러를 발생함")
    void minAgeGreaterThanMaxTest() {
        // given
        int min = 40;
        int max = 20;

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new AgeRange(min, max));
    }

    @Test
    @DisplayName("최소 나이가 0보다 작거나 최대 나이가 0보다 작으면 에러 발생")
    void minAgeLessThanMinTest() {
        // given
        int min = -1;
        int max = -1;

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new AgeRange(min, 0));
        assertThrows(IllegalArgumentException.class, () -> new AgeRange(0, max));
    }

    @Test
    @DisplayName("최대 나이가 99보다 크면 에러 발생")
    void maxAgeLessThanMaxTest() {
        // given
        int min = 20;
        int max = 100;

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new AgeRange(min, max));
    }

    @Test
    @DisplayName("설정한 연령대에 맞다면 true반환")
    void avaliableAge_success() {
        // given
        AgeRange range = new AgeRange(20, 30);

        // when, then
        assertTrue(range.isAvaliableAge(20));
        assertTrue(range.isAvaliableAge(30));
    }

    @Test
    @DisplayName("설정한 연령대를 벗어난다면 false반환")
    void avaliableAge_fail() {
        // given
        AgeRange range = new AgeRange(20, 30);

        // when, then
        assertFalse(range.isAvaliableAge(31));
        assertFalse(range.isAvaliableAge(19));
    }

    @Test
    @DisplayName("연령 제한이 없다면 true반환")
    void avaliableAge_true() {
        // given
        AgeRange range = new AgeRange(null, null);

        // when, then
        assertTrue(range.isAvaliableAge(20));
        assertTrue(range.isAvaliableAge(30));
        assertTrue(range.isAvaliableAge(40));
    }
}