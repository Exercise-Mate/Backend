package com.f3.exercise_mate.chat.controller;

import com.f3.exercise_mate.chat.entity.ChatRoom;
import com.f3.exercise_mate.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "http://localhost:5173") // 클라이언트의 도메인을 허용
public class RoomController {

    private final ChatRoomRepository chatRoomRepository;

    // 채팅방 개설
    @PostMapping(value = "/chat-room")
    public ResponseEntity<String> create(@RequestBody String chatRoomName){
        log.info("# Create Chat Room , name: " + chatRoomName);

        ChatRoom chatRoom = chatRoomRepository.createChatRoom(chatRoomName);
        return ResponseEntity.ok(chatRoom.getId());
    }

    // 채팅방 목록 조회
    @GetMapping(value = "/chat-rooms")
    public ResponseEntity<List<ChatRoom>> rooms(){
        log.info("# All Chat Rooms");

        List<ChatRoom> allChatRooms = chatRoomRepository.findAllRooms();
        return ResponseEntity.ok(allChatRooms);
    }

    // 채팅방 조회
    @GetMapping("/chat-room")
    public ResponseEntity<String> getRoom(@RequestBody String chatRoomId){
        log.info("# get Chat Room, roomID : " + chatRoomId);

        ChatRoom foundRoom = chatRoomRepository.findRoomById(chatRoomId);
        return ResponseEntity.ok(foundRoom.getId());
    }
}