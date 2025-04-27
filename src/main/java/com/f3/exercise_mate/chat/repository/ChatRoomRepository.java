package com.f3.exercise_mate.chat.repository;

import com.f3.exercise_mate.chat.entity.ChatRoom;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> ChatRoomMap;

    @PostConstruct
    private void init(){
        ChatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRooms(){
        // 채팅방 생성 순서 최근 순으로 반환
        List<ChatRoom> result = new ArrayList<>(ChatRoomMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoom findRoomById(String id){
        return ChatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name){
        ChatRoom room = ChatRoom.create(name);
        ChatRoomMap.put(room.getId(), room);

        return room;
    }
}