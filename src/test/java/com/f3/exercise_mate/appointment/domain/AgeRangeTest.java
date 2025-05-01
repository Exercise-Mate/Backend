package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.appointment.application.exception.AppointmentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

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
        assertEquals(0, unrestricted.getMinAge());
        assertEquals(100, unrestricted.getMaxAge());
    }

    @Test
    @DisplayName("최소 나이가 최대 나이보다 많으면 에러를 발생함")
    void minAgeGreaterThanMaxTest() {
        // given
        int min = 40;
        int max = 20;

        // when, then
        assertThrows(AppointmentException.class, () -> new AgeRange(min, max));
    }

    @Test
    @DisplayName("최소 나이가 0보다 작거나 최대 나이가 0보다 작으면 에러 발생")
    void minAgeLessThanMinTest() {
        // given
        int min = -1;
        int max = -1;

        // when, then
        assertThrows(AppointmentException.class, () -> new AgeRange(min, 0));
        assertThrows(AppointmentException.class, () -> new AgeRange(0, max));
    }

    @Test
    @DisplayName("최대 나이가 100보다 크면 에러 발생")
    void maxAgeLessThanMaxTest() {
        // given
        int min = 20;
        int max = 101;

        // when, then
        assertThrows(AppointmentException.class, () -> new AgeRange(min, max));
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

    @ParameterizedTest
    @ValueSource(ints = {0, 19, 31, 100})
    @DisplayName("설정한 연령대를 벗어난다면 false반환")
    void availableAge_fail(int value) {
        // given
        AgeRange range = new AgeRange(20, 30);

        // when, then
        assertFalse(range.isAvaliableAge(31));
        assertFalse(range.isAvaliableAge(19));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10, 18, 100})
    @DisplayName("연령 제한이 없다면 true반환")
    void availableAge_true(int value) {
        // given
        AgeRange range = new AgeRange(null, null);

        // when, then
        assertTrue(range.isAvaliableAge(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10, 18, 20})
    @DisplayName("최소나이가 null일 때 MIN_AGE = 0 으로 지정되어 0~max에 해당하는 나이는 참석가능")
    void minAgeIsNull_whenIsAvailableAgeRange_thenSuccess(int value) {
        // given
        AgeRange range = new AgeRange(null, 20);

        // when, then
        assertTrue(range.isAvaliableAge(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {21, 30, 40, 101})
    @DisplayName("최소나이가 null일 때 MIN_AGE = 0 으로 지정되어 0~max에 해당하지않는 나이는 참석 불가능")
    void minAgeIsNull_WhenIsNotAvailableAgeRange_thenThrowError(int value) {
        // given
        AgeRange range = new AgeRange(null, 20);

        // when, then
        assertFalse(range.isAvaliableAge(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {20, 30, 99})
    @DisplayName("최대연령이 null일 경우 MAX_AGE = 100 으로 지정되어 min ~ 100에 해당하는 나이는 참석가능")
    void maxAgeIsNull_createAgeRange_thenMaxAgeShould100(int value) {
        // given
        AgeRange range = new AgeRange(20, null);

        // when, then
        assertTrue(range.isAvaliableAge(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10, 19})
    @DisplayName("최소나이가 null일 때 MIN_AGE = 0 으로 지정되어 min ~ 100에 해당하지않는 나이는 참석 불가능")
    void maxAgeIsNull_WhenIsNotAvailableAgeRange_thenThrowError(int value) {
        // given
        AgeRange range = new AgeRange(20, null);

        // when, then
        assertFalse(range.isAvaliableAge(value));
    }

}