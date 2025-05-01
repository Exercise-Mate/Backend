package com.f3.exercise_mate.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessage {

    private String chatRoomId;
    private String writer;
    private String message;
}