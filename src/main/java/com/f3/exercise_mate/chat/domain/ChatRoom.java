package com.f3.exercise_mate.chat.domain;

import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    private Long id;
    private Long memberId;
    private String name;

    /**
     * 생성 메서드
     */
    public static ChatRoom create(Long id, Long memberId, String name){
        ChatRoom room = new ChatRoom();

        room.id = id;
        room.memberId = memberId;
        room.name = name;

        room.validate();
        return room;
    }

    // 유효성 검증
    private void validate() {
        if (memberId == null) {
            throw new ExerciseMateException(ErrorCode.CHATROOM_CREATE_MEMBER_ID_NOT_FOUND);
        }
        if (name == null || name.trim().isEmpty()) {
            throw new ExerciseMateException(ErrorCode.CHATROOM_CREATE_NAME_NOT_FOUND);
        }
    }
}
