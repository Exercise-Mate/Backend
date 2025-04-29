package com.f3.exercise_mate.club.exception;

public enum ClubErrorCode {
    CLUB_CREATE_NAME_NOT_FOUND("클럽명은 필수입니다."),
    CLUB_CREATE_DESCRIPTION_NOT_FOUND("클럽소개는 필수입니다."),
    CLUB_CREATE_SPORT_TYPE_NOT_FOUND("운동 종목은 필수입니다."),
    CLUB_CREATE_MAIN_AREA_NOT_FOUND("주 활동 지역은 필수입니다."),
    CLUB_CREATE_SPORT_ABILITY_MIN_NOT_FOUND("운동 실력 최소는 필수입니다."),
    CLUB_CREATE_SPORT_ABILITY_MAX_NOT_FOUND("운동 실력 최대는 필수입니다.");

    private final String errorMessage;

    ClubErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
