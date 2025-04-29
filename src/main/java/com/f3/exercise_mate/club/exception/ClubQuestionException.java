package com.f3.exercise_mate.club.exception;

public class ClubQuestionException extends RuntimeException {
    private final ClubQuestionErrorCode clubQuestionErrorCode;

    public ClubQuestionException(ClubQuestionErrorCode clubQuestionErrorCode) {
        super(clubQuestionErrorCode.getErrorMessage());
        this.clubQuestionErrorCode = clubQuestionErrorCode;
    }

    public String getErrorCode() {
        return clubQuestionErrorCode.name();
    }
}
