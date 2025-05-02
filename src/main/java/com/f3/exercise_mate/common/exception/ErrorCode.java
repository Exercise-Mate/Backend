package com.f3.exercise_mate.common.exception;

public enum ErrorCode {
    // 📌 Appointment
    APPOINTMENT_TITLE_REQUIRED("A001", "약속 제목은 필수입니다."),
    APPOINTMENT_TITLE_LENGTH_INVALID("A002", "약속 제목은 5~100자여야 합니다."),
    APPOINTMENT_DESCRIPTION_REQUIRED("A003", "약속 설명은 필수입니다."),
    APPOINTMENT_DESCRIPTION_LENGTH_INVALID("A004", "약속 설명은 5~500자여야 합니다."),
    APPOINTMENT_MODIFY_CREATOR_ONLY("A005", "약속 생성자만 수정할 수 있습니다."),
    APPOINTMENT_DATE_REQUIRED("A006", "날짜, 시작시간, 종료시간은 모두 필수입니다."),
    APPOINTMENT_DATE_PAST_INVALID("A007", "약속의 날짜는 현재일 이후여야 합니다."),
    APPOINTMENT_TIME_INVALID("A008", "종료시간은 시작시간보다 빠르거나 같을 수 없습니다."),

    // 📌 AgeRange
    AGE_RANGE_NEGATIVE("AR001", "나이는 0보다 작을 수 없습니다."),
    AGE_RANGE_MIN_OVER_MAX("AR002", "최소 나이는 최대 나이보다 작아야 합니다."),
    AGE_RANGE_EXCEED_MAX("AR003", "최대 나이는 99세입니다."),
    AGE_RANGE_AGE_REQUIRED("AR004", "올바른 나이를 입력하세요."),
    AGE_RANGE_NOT_ACCEPTED("AR005", "연령 제한에 부합하지 않습니다."),

    // 📌 LevelRange
    LEVEL_RANGE_INVALID("L001", "최소 레벨은 최대 레벨보다 낮거나 같아야 합니다."),

    // 📌 Participants
    PARTICIPANT_OVERFLOW("P001", "최대 참여 인원을 초과했습니다."),
    PARTICIPANT_NULL("P002", "유저는 null일 수 없습니다."),
    PARTICIPANT_DUPLICATE("P003", "이미 참가하고 있는 유저입니다."),
    PARTICIPANT_NOT_FOUND("P004", "참여자 목록에 없는 유저입니다."),

    // 📌 Question
    QUESTION_EMPTY("Q001", "질문은 비어 있으면 안됩니다."),
    QUESTION_TOO_SHORT("Q002", "질문의 최소 길이는 5자입니다."),
    QUESTION_TOO_LONG("Q003", "질문의 최대 길이는 200자입니다."),
    QUESTION_LIST_NULL("Q004", "질문 리스트에 null이 포함될 수 없습니다."),
    QUESTION_NULL("Q005", "질문은 null일 수 없습니다."),

    // Club
    CLUB_CREATE_NAME_NOT_FOUND("C001", "클럽명은 필수입니다."),
    CLUB_CREATE_DESCRIPTION_NOT_FOUND("C002", "클럽소개는 필수입니다."),
    CLUB_CREATE_SPORT_TYPE_NOT_FOUND("C003", "운동 종목은 필수입니다."),
    CLUB_CREATE_MAIN_AREA_NOT_FOUND("C004", "주 활동 지역은 필수입니다."),
    CLUB_CREATE_SPORT_ABILITY_MIN_NOT_FOUND("C005", "운동 실력 최소는 필수입니다."),
    CLUB_CREATE_SPORT_ABILITY_MAX_NOT_FOUND("C006", "운동 실력 최대는 필수입니다."),

    // ClubQuestion
    CLUB_CREATE_JOIN_QUESTION_TOO_SHORT("CQ001", "질문은 최소 5자 이상이어야 합니다."),
    CLUB_CREATE_JOIN_QUESTION_TOO_LONG("CQ002", "질문은 최대 200자까지 입력 가능합니다."),

    // Chat
    CHATROOM_CREATE_MEMBER_ID_NOT_FOUND("CH001", "채팅방을 생성하는 회원의 ID값은 필수입니다."),
    CHATROOM_CREATE_NAME_NOT_FOUND("CH001", "생성할 채팅방의 이름은 필수입니다."),
    CHATROOM_NOT_FOUND("CH003", "해당 채팅방을 찾을 수 없습니다."),
    CHATROOM_CREATE_ID_NOT_MATCH("CH004", "생성된 채팅방 ID가 예상값과 일치하지 않습니다.");


    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return message;
    }
}
