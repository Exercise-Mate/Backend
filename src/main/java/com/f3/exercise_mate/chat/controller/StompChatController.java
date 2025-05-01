package com.f3.exercise_mate.chat.controller;

import com.f3.exercise_mate.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template;   // 특정 Broker로 메세지를 전달

    // Client가 send 할 수 있는 경로
    // stompConfig에서 설정한 setApplicationDestinationPrefixes의 경로와 @MessageMapping 경로가 병합됨
    // "/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessage message) {
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessage message) {
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }
}