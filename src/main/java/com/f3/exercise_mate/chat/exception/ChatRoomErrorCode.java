package com.f3.exercise_mate.chat.exception;

import lombok.Getter;

@Getter
public enum ChatRoomErrorCode {

    CHATROOM_CREATE_MEMBER_ID_NOT_FOUND("채팅방을 생성하는 회원의 ID값은 필수입니다."),
    CHATROOM_CREATE_NAME_NOT_FOUND("생성할 채팅방의 이름은 필수입니다."),
    CHATROOM_NOT_FOUND("해당 채팅방을 찾을 수 없습니다."),
    CHATROOM_CREATE_ID_NOT_MATCH("생성된 채팅방 ID가 예상값과 일치하지 않습니다.");

    private final String errorMessage;

    ChatRoomErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
