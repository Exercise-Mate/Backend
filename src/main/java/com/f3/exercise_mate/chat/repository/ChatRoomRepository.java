package com.f3.exercise_mate.chat.repository;

import com.f3.exercise_mate.chat.domain.ChatRoom;

import java.util.*;

public interface ChatRoomRepository {

    Long save(Long memberId, String name);
    List<ChatRoom> findAll();
    ChatRoom findById(Long id);

}