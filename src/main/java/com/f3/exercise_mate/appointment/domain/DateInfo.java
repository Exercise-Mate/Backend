package com.f3.exercise_mate.appointment.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class DateInfo {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public DateInfo(LocalDate date, LocalTime startTime, LocalTime endTime) {
        checkDate(date, startTime, endTime);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private void checkDate(LocalDate date, LocalTime startTime, LocalTime endTime) {
        if(date == null || startTime == null || endTime == null) {
            throw new IllegalArgumentException("날짜, 시작시간, 종료시간은 모두 필수 입니다.");
        }

        if(date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("약속의 날짜는 현재일 이후로 설정해야 합니다.");
        }

        if(startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("종료시간은 시작시간보다 빠르거나 같을 수 없습니다.");
        }
    }
}
