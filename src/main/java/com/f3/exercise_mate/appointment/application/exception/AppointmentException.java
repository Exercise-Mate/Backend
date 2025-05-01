package com.f3.exercise_mate.appointment.application.exception;

public class AppointmentException extends RuntimeException {
    private final AppointmentErrorCode errorCode;

    public AppointmentException(AppointmentErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AppointmentErrorCode getErrorCode() {
        return errorCode;
    }
}
