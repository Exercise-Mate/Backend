package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantsTest {

    private User createUser() {
        return new User("john");
    }

    @Test
    @DisplayName("참가자 리스트가 정상적으로 생성된다.")
    void createParticipants_create_success() {
        // given
        Participants participants1 = new Participants();

        // when, then
        assertEquals(0, participants1.size());
    }

    @Test
    @DisplayName("참여자 추가 성공")
    void participantsAdd_success() {
        // given
        Participants participants = new Participants();
        User user = createUser();

        // when
        participants.add(user);

        // then
        assertEquals(1, participants.size());
    }

    @Test
    @DisplayName("이미 참가중인 참여자일 경우 에러 발생")
    void participantsAdd_alreadyUser_throwError() {
        // given
        Participants participants = new Participants();
        User user = createUser();

        participants.add(user);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> participants.add(user));
    }

    @Test
    @DisplayName("null 참여자 추가 실패")
    void participantsAdd_nullUser_throwError() {
        // given
        Participants participants = new Participants();

        // when, then
        assertThrows(IllegalArgumentException.class, () -> participants.add(null));
    }

    @Test
    @DisplayName("최대 참여 인원 초과시 참여 실패")
    void participantsMax_add_throwError() {
        // given
        User creator = createUser();
        Participants participants = new Participants(List.of(creator), 1);
        User user = new User("park");
        // when, then
        assertThrows(IllegalArgumentException.class, () -> participants.add(user));
    }

    @Test
    @DisplayName("참여자 탈퇴 성공")
    void participantsRemove_success() {
        // given
        Participants participants = new Participants();
        User user = createUser();
        participants.add(user);

        // when
        participants.remove(user);

        // then
        assertEquals(0, participants.size());
    }

    @Test
    @DisplayName("참여자가 null일때 탈퇴시 에러발생")
    void participantsRemove_nullUser_throwError() {
        // given
        Participants participants = new Participants();
        User user = createUser();
        participants.add(user);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> participants.remove(null));
    }

    @Test
    @DisplayName("참여하고 있는 user가 아닐때 탈퇴시 에러발생")
    void participantsRemove_wrongUser_throwError() {
        // given
        Participants participants = new Participants();
        User user = createUser();
        participants.add(user);

        // when
        User worngUser = new User("park");

        // then
        assertThrows(IllegalArgumentException.class, () -> participants.remove(worngUser));
    }
}