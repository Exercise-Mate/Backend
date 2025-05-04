package com.f3.exercise_mate.chat.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {
    private Long id;
    private Long memberId;
    private Long chatRoomId;
    private String message;
    private String sendAt;

    public static ChatMessage create(Long id, Long memberId, Long chatRoomId, String message, String sendAt) {
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.id = id;
        chatMessage.memberId = memberId;
        chatMessage.chatRoomId = chatRoomId;
        chatMessage.message = message;
        chatMessage.sendAt = sendAt;

        chatMessage.validate();
        return chatMessage;
    }

    // 유효성 검증
    private void validate() {

    }
}