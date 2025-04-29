package com.f3.exercise_mate.club.exception;

public enum ClubQuestionErrorCode {
    CLUB_CREATE_JOIN_QUESTION_TOO_SHORT("질문은 최소 5자 이상이어야 합니다."),
    CLUB_CREATE_JOIN_QUESTION_TOO_LONG("질문은 최대 200자까지 입력 가능합니다.");

    private final String errorMessage;

    ClubQuestionErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
