package com.f3.exercise_mate.club.exception;

public class ClubException extends RuntimeException {
    private final ClubErrorCode clubErrorCode;

    public ClubException(ClubErrorCode clubErrorCode) {
        super(clubErrorCode.getErrorMessage());
        this.clubErrorCode = clubErrorCode;
    }

    public String getErrorCode() {
        return clubErrorCode.name();
    }
}
