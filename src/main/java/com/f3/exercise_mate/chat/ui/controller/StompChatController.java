package com.f3.exercise_mate.chat.ui.controller;

import com.f3.exercise_mate.chat.application.dto.ChatMessageRequestDto;
import com.f3.exercise_mate.chat.application.dto.EnterChatRoomRequestDTO;
import com.f3.exercise_mate.chat.ui.dto.ChatMessageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template;   // 특정 Broker로 메세지를 전달

    // stompConfig에서 설정한 setApplicationDestinationPrefixes의 경로와 @MessageMapping 경로가 병합됨
    // /app/chat/enter
    @MessageMapping(value = "/chat/enter")
    public void enter(EnterChatRoomRequestDTO enterChatRoomRequestDTO) {
        String message = "[회원 id: " + enterChatRoomRequestDTO.memberId() + "] 님이 채팅방에 참여하였습니다.";
        ChatMessageResponseDTO chatMessageResponseDTO = new ChatMessageResponseDTO(enterChatRoomRequestDTO.memberId(), message, enterChatRoomRequestDTO.sendAt());

        template.convertAndSend("/topic/chat/room/" + enterChatRoomRequestDTO.chatRoomId(), chatMessageResponseDTO);
    }

    // /app/chat/chatMessageReqDto
    @MessageMapping(value = "/chat/message")
    public void chatMessageReqDto(ChatMessageRequestDto chatMessageRequestDto) {
        ChatMessageResponseDTO chatMessageResponseDTO = new ChatMessageResponseDTO(chatMessageRequestDto.memberId(), chatMessageRequestDto.message(), chatMessageRequestDto.sendAt());

        template.convertAndSend("/topic/chat/room/" + chatMessageRequestDto.chatRoomId(), chatMessageResponseDTO);
        log.info(chatMessageRequestDto.message());
    }
}