package com.f3.exercise_mate.chat.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    private String id;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();   // WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

    /**
     * 생성 메서드
     */
    public static ChatRoom create(String name){
        ChatRoom room = new ChatRoom();

        room.id = UUID.randomUUID().toString();
        room.name = name;

        return room;
    }
}
