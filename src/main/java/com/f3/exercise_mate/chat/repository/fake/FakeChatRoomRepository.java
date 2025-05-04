package com.f3.exercise_mate.chat.repository.fake;

import com.f3.exercise_mate.chat.application.interfaces.ChatRoomRepository;
import com.f3.exercise_mate.chat.domain.ChatRoom;
import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class FakeChatRoomRepository implements ChatRoomRepository {

    private Long chatRoomId = 1L;
    private final Map<Long, ChatRoom> ChatRoomMap = new ConcurrentHashMap<>();

    @Override
    public Long save(Long memberId, String name) {
        ChatRoom room = ChatRoom.create(chatRoomId++, memberId, name);

        if (room.getId() != chatRoomId - 1) {
            throw new ExerciseMateException(ErrorCode.CHATROOM_CREATE_ID_NOT_MATCH);
        }

        ChatRoomMap.put(room.getId(), room);
        return room.getId();
    }

    @Override
    public List<ChatRoom> findAll() {
        // 채팅방 생성 순서 최근 순으로 반환
        List<ChatRoom> allRooms = new ArrayList<>(ChatRoomMap.values());
        Collections.reverse(allRooms);

        return allRooms;
    }

    @Override
    public ChatRoom findById(Long id) {
        ChatRoom foundRoom = ChatRoomMap.get(id);
        return foundRoom;
    }

}
