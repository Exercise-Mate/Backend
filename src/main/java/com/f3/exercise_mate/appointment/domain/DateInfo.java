package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DateInfo {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public DateInfo(LocalDate date, LocalTime startTime, LocalTime endTime) {
        checkDate(date, startTime, endTime);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private void checkDate(LocalDate date, LocalTime startTime, LocalTime endTime) {
        if(date == null || startTime == null || endTime == null) {
            throw new ExerciseMateException(ErrorCode.APPOINTMENT_DATE_REQUIRED);
        }

        if(date.isBefore(LocalDate.now())) {
            throw new ExerciseMateException(ErrorCode.APPOINTMENT_DATE_PAST_INVALID);
        }

        if(startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new ExerciseMateException(ErrorCode.APPOINTMENT_TIME_INVALID);
        }
    }
}
