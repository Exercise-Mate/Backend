package com.f3.exercise_mate.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessage {
    private Long id;
    private Long memberId;
    private Long chatRoomId;
    private String message;
    private String send_at;
}