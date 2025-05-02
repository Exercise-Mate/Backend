package com.f3.exercise_mate.common.exception;

public class ExerciseMateException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExerciseMateException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode.name();
    }

    public String getFullError() {
        return "[" + errorCode.name() + "] " + errorCode.getErrorMessage();
    }
}
