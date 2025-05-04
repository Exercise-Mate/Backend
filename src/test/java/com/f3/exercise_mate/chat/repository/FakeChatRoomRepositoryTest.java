package com.f3.exercise_mate.chat.repository;

import com.f3.exercise_mate.chat.domain.ChatRoom;
import com.f3.exercise_mate.chat.repository.fake.FakeChatRoomRepository;
import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakeChatRoomRepositoryTest {

    private FakeChatRoomRepository fakeChatRoomRepository;

    @BeforeEach
    void setUp() {
        fakeChatRoomRepository = new FakeChatRoomRepository();
    }

    @Test
    @DisplayName("채팅방 개설 성공")
    void 채팅방_개설_성공() {
        //given
        Long memberId = 100L;
        String chatRoomName = "테니스방";

        //when
        Long savedRoomId = fakeChatRoomRepository.save(memberId, chatRoomName);

        //then
        assertEquals(1, savedRoomId);
    }

    @Test
    @DisplayName("채팅방 개설 시, 채팅방 id값이 1씩 정상적으로 증가")
    void 채팅방id_1씩_증가_성공() {
        //given
        Long memberId = 100L;
        String chatRoomName = "테니스방";
        Long memberId2 = 200L;
        String chatRoomName2 = "농구방";
        Long memberId3 = 300L;
        String chatRoomName3 = "클라이밍방";

        //when
        Long savedRoomId = fakeChatRoomRepository.save(memberId, chatRoomName);
        Long savedRoomId2 = fakeChatRoomRepository.save(memberId2, chatRoomName2);
        Long savedRoomId3 = fakeChatRoomRepository.save(memberId3, chatRoomName3);

        //then
        assertEquals(1, savedRoomId);
        assertEquals(2, savedRoomId2);
        assertEquals(3, savedRoomId3);
    }

    @Test
    @DisplayName("멤버id가 null이면 예외 발생")
    void 멤버id_null_예외() {
        //given
        Long id = 1L;
        Long memberId = null;
        String chatRoomName = "테니스방";

        //when, then
        ExerciseMateException exception = assertThrows(ExerciseMateException.class, () -> fakeChatRoomRepository.save(memberId, chatRoomName));

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
        ExerciseMateException exception = assertThrows(ExerciseMateException.class, () -> fakeChatRoomRepository.save(memberId, chatRoomName));

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
        ExerciseMateException exception = assertThrows(ExerciseMateException.class, () -> fakeChatRoomRepository.save(memberId, chatRoomName));

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
        ExerciseMateException exception = assertThrows(ExerciseMateException.class, () -> fakeChatRoomRepository.save(memberId, chatRoomName));

        assertEquals(ErrorCode.CHATROOM_CREATE_NAME_NOT_FOUND.name(), exception.getErrorCode());
    }

    @Test
    @DisplayName("채팅방 전체 조회 시, 최신순으로 정렬되어 반환")
    void 채팅방_전체조회_최신순() {
        // given
        Long id1 = fakeChatRoomRepository.save(1L, "테니스방");
        Long id2 = fakeChatRoomRepository.save(2L, "농구방");
        Long id3 = fakeChatRoomRepository.save(3L, "클라이밍방");

        // when
        List<ChatRoom> allRooms = fakeChatRoomRepository.findAll();

        // then
        assertEquals(3, allRooms.size());
        assertEquals("클라이밍방", allRooms.get(0).getName());
        assertEquals(id3, allRooms.get(0).getId());

        assertEquals("농구방", allRooms.get(1).getName());
        assertEquals(id2, allRooms.get(1).getId());

        assertEquals("테니스방", allRooms.get(2).getName());
        assertEquals(id1, allRooms.get(2).getId());

    }

    @Test
    @DisplayName("ID로 채팅방 조회 성공")
    void 채팅방_ID조회_성공() {
        // given
        Long id = fakeChatRoomRepository.save(1L, "테니스방");

        // when
        ChatRoom foundRoom = fakeChatRoomRepository.findById(id);

        // then
        assertNotNull(foundRoom);
        assertEquals(1L, foundRoom.getMemberId());
        assertEquals("테니스방", foundRoom.getName());
    }

    @Test
    @DisplayName("존재하지 않는 ID로 채팅방 조회 시 null 반환")
    void 채팅방_ID조회_실패() {
        // when
        ChatRoom foundRoom = fakeChatRoomRepository.findById(-1L);

        // then
        assertNull(foundRoom);
    }
}