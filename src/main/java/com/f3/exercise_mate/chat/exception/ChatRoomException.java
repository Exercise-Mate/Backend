package com.f3.exercise_mate.chat.exception;

public class ChatRoomException extends RuntimeException {
    private final ChatRoomErrorCode chatRoomErrorCode;

    public ChatRoomException(ChatRoomErrorCode chatRoomErrorCode) {
        super(chatRoomErrorCode.getErrorMessage());
        this.chatRoomErrorCode = chatRoomErrorCode;
    }

    public String getErrorCode() {
        return chatRoomErrorCode.name();
    }

    public String getFullError() {
        return "[" + chatRoomErrorCode.name() + "] " + chatRoomErrorCode.getErrorMessage();
    }
}
