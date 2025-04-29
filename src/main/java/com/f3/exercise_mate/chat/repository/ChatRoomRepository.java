package com.f3.exercise_mate.chat.repository;

import com.f3.exercise_mate.chat.entity.ChatRoom;
import com.f3.exercise_mate.chat.exception.ChatRoomErrorCode;
import com.f3.exercise_mate.chat.exception.ChatRoomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {

    private Long chatRoomId = 1L;
    private final Map<Long, ChatRoom> ChatRoomMap = new ConcurrentHashMap<>();

    public Long save(Long memberId, String name){
        ChatRoom room = ChatRoom.create(chatRoomId++, memberId, name);

        if(room.getId() != chatRoomId - 1){
            throw new ChatRoomException(ChatRoomErrorCode.CHATROOM_CREATE_ID_NOT_MATCH);
        }

        ChatRoomMap.put(room.getId(), room);
        return room.getId();
    }

    public List<ChatRoom> findAll(){
        // 채팅방 생성 순서 최근 순으로 반환
        List<ChatRoom> allRooms = new ArrayList<>(ChatRoomMap.values());
        Collections.reverse(allRooms);

        return allRooms;
    }

    public ChatRoom findById(Long id){
        ChatRoom foundRoom = ChatRoomMap.get(id);
        return foundRoom;
    }



}