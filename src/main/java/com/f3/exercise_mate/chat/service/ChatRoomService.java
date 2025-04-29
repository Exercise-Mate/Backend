package com.f3.exercise_mate.chat.service;

import com.f3.exercise_mate.chat.entity.ChatRoom;
import com.f3.exercise_mate.chat.exception.ChatRoomErrorCode;
import com.f3.exercise_mate.chat.exception.ChatRoomException;
import com.f3.exercise_mate.chat.repository.ChatRoomRepository;
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

    public Long create(Long memberId, String name) {
        return chatRoomRepository.save(memberId, name);
    }

    public List<ChatRoom> findAll() {
        List<ChatRoom> allRooms = chatRoomRepository.findAll();
        return allRooms;
    }

    public ChatRoom findById(Long id) {
        ChatRoom foundRoom = chatRoomRepository.findById(id);
        if (foundRoom == null) {
            throw new ChatRoomException(ChatRoomErrorCode.CHATROOM_NOT_FOUND);
        }
        return foundRoom;
    }
}
