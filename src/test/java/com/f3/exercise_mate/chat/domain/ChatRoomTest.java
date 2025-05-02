package com.f3.exercise_mate.chat.domain;

import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomTest {

    @Test
    @DisplayName("채팅방 객체 생성 성공")
    void 채팅방_객체_생성_성공() {
        //given
        Long id = 1L;
        Long memberId = 100L;
        String chatRoomName = "테니스방";

        //when
        ChatRoom chatRoom = ChatRoom.create(id, memberId, chatRoomName);

        //then
        assertNotNull(chatRoom);
        assertEquals(id, chatRoom.getId());
        assertEquals(memberId, chatRoom.getMemberId());
        assertEquals(chatRoomName, chatRoom.getName());
    }

    @Test
    @DisplayName("멤버id가 null이면 예외 발생")
    void 멤버id_null_예외() {
        //given
        Long id = 1L;
        Long memberId = null;
        String chatRoomName = "테니스방";

        //when, then
        ExerciseMateException exception = assertThrows(ExerciseMateException.class, () -> ChatRoom.create(id, memberId, chatRoomName));

        assertEquals(ErrorCode.CHATROOM_CREATE_MEMBER_ID_NOT_FOUND.name(), exception.getErrorCode());
    }

    @Test
    @DisplayName("채팅방 이름이 null이면 예외 발생")
    void 채팅방명_null_예외() {
        //given
        Long id = 1L;
        Long memberId = 100L;
        String chatRoomName = null;

        //when, then
        ExerciseMateException exception = assertThrows(ExerciseMateException.class, () -> ChatRoom.create(id, memberId, chatRoomName));

        assertEquals(ErrorCode.CHATROOM_CREATE_NAME_NOT_FOUND.name(), exception.getErrorCode());
    }

    @Test
    @DisplayName("채팅방 이름이 empty면 예외 발생")
    void 채팅방명_empty_예외() {
        //given
        Long id = 1L;
        Long memberId = 100L;
        String chatRoomName = "";

        //when, then
        ExerciseMateException exception = assertThrows(ExerciseMateException.class, () -> ChatRoom.create(id, memberId, chatRoomName));

        assertEquals(ErrorCode.CHATROOM_CREATE_NAME_NOT_FOUND.name(), exception.getErrorCode());
    }

    @Test
    @DisplayName("채팅방 이름이 공백이면 예외 발생")
    void 채팅방명_공백_예외() {
        //given
        Long id = 1L;
        Long memberId = 100L;
        String chatRoomName = "     \t              ";

        //when, then
        ExerciseMateException exception = assertThrows(ExerciseMateException.class, () -> ChatRoom.create(id, memberId, chatRoomName));

        assertEquals(ErrorCode.CHATROOM_CREATE_NAME_NOT_FOUND.name(), exception.getErrorCode());
    }

}