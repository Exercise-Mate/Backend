package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.appointment.application.exception.AppointmentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DateInfoTest {

    @Test
    @DisplayName("약속의 일시 정보가 정삭적으로 입력되면 정상적으로 생성된다.")
    void createDateInfo_success() {
        // given
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusHours(1);

        // when
        DateInfo dateInfo = new DateInfo(date, startTime, endTime);

        // then
        assertEquals(date, dateInfo.getDate());
        assertEquals(startTime, dateInfo.getStartTime());
        assertEquals(endTime, dateInfo.getEndTime());
    }

    @Test
    @DisplayName("약속의 일자가 현재보다 과거라면 에러발생")
    void createDateInfo_dateIsBeforeNow_throwError() {
        // given
        LocalDate date = LocalDate.now().minusDays(1);
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusHours(1);

        // when, then
        assertThrows(AppointmentException.class, () -> new DateInfo(date, startTime, endTime));
    }

    @Test
    @DisplayName("약속의 종료시간이 시작시간 보다 빠르면 에러발생")
    void createDateInfo_endTimeBeforeThanStartTime_throwError() {
        // given
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = startTime.minusHours(1);

        // when, then
        assertThrows(AppointmentException.class, () -> new DateInfo(date, startTime, endTime));
    }

    @Test
    @DisplayName("Null인 매개변수가 들어온다면 에러발생")
    void createDateInfo_startTimeIsNull_throwError() {
        // given
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusHours(1);

        // when, then
        assertThrows(AppointmentException.class, () -> new DateInfo(null, startTime, endTime));
        assertThrows(AppointmentException.class, () -> new DateInfo(date, null, endTime));
        assertThrows(AppointmentException.class, () -> new DateInfo(date, startTime, null));
    }
}