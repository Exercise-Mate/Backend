package com.f3.exercise_mate.chat.application.service;

import com.f3.exercise_mate.chat.domain.ChatRoom;
import com.f3.exercise_mate.chat.application.dto.CreateChatRoomRequestDTO;
import com.f3.exercise_mate.chat.application.interfaces.ChatRoomRepository;
import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Long create(CreateChatRoomRequestDTO createChatRoomRequestDTO) {
        return chatRoomRepository.save(createChatRoomRequestDTO.memberId(), createChatRoomRequestDTO.chatRoomName());
    }

    // (임시) 채팅방 목록에서 보여야할 정보들을 추가 구성해야함
    public List<ChatRoom> findAll() {
        List<ChatRoom> allRooms = chatRoomRepository.findAll();
        return allRooms;
    }

    public ChatRoom findById(Long id) {
        ChatRoom foundRoom = chatRoomRepository.findById(id);
        if (foundRoom == null) {
            throw new ExerciseMateException(ErrorCode.CHATROOM_NOT_FOUND);
        }
        return foundRoom;
    }
}
