package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.appointment.application.exception.AppointmentErrorCode;
import com.f3.exercise_mate.appointment.application.exception.AppointmentException;
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
            throw new AppointmentException(AppointmentErrorCode.APPOINTMENT_DATE_REQUIRED);
        }

        if(date.isBefore(LocalDate.now())) {
            throw new AppointmentException(AppointmentErrorCode.APPOINTMENT_DATE_PAST_INVALID);
        }

        if(startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new AppointmentException(AppointmentErrorCode.APPOINTMENT_TIME_INVALID);
        }
    }
}
