package com.f3.exercise_mate.chat.controller;

import com.f3.exercise_mate.chat.dto.CreateRoomReqDTO;
import com.f3.exercise_mate.chat.entity.ChatRoom;
import com.f3.exercise_mate.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:5173") // 클라이언트의 도메인을 허용
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅방 개설
    @PostMapping(value = "/chat-room")
    public ResponseEntity<Long> create(@RequestBody CreateRoomReqDTO request){
        log.info("# Create Chat Room , name: " + request.getChatRoomName());

        Long createdRoomId = chatRoomService.create(request.getMemberId(), request.getChatRoomName());
        return ResponseEntity.ok(createdRoomId);
    }

    // 특정 채팅방 조회
    @GetMapping("/chat-room")
    public ResponseEntity<Long> roomById(@RequestBody Long chatRoomId){
        log.info("# get Chat Room, roomID : " + chatRoomId);

        ChatRoom foundRoom = chatRoomService.findById(chatRoomId);
        return ResponseEntity.ok(foundRoom.getId());
    }

    // 채팅방 목록 조회
    @GetMapping(value = "/chat-rooms")
    public ResponseEntity<List<ChatRoom>> rooms(){
        log.info("# All Chat Rooms");

        List<ChatRoom> allRooms = chatRoomService.findAll();
        return ResponseEntity.ok(allRooms);
    }


}