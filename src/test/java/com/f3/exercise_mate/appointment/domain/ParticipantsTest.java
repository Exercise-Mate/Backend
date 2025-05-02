package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.common.exception.ExerciseMateException;
import com.f3.exercise_mate.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantsTest {

    private final User creator = new User(1L, 20, "john");
    private final User user = new User(2L, 20, "jane");
    private final Location location = new Location("서울시 강남구", "강남 풋살장", "");
    private final DateInfo dateInfo = new DateInfo(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1));
    private final Appointment appointment = Appointment.create(
            1L,
            "풋살 한판 뛰실분?",
            creator,
            "풋살 재밌게 찹시다.",
            Sport.FUTSAL,
            location,
            dateInfo,
            10);
    private Participant participant1 = new Participant(creator, appointment);
    private Participant participant2 = new Participant(user, appointment);

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

        // when
        participants.add(participant1);

        // then
        assertEquals(1, participants.size());
    }

    @Test
    @DisplayName("이미 참가중인 참여자일 경우 에러 발생")
    void participantsAdd_alreadyUser_throwError() {
        // given
        Participants participants = new Participants();

        participants.add(participant1);

        // when, then
        assertThrows(ExerciseMateException.class, () -> participants.add(participant1));
    }

    @Test
    @DisplayName("null 참여자 추가 실패")
    void participantsAdd_nullUser_throwError() {
        // given
        Participants participants = new Participants();

        // when, then
        assertThrows(ExerciseMateException.class, () -> participants.add(null));
    }

    @Test
    @DisplayName("최대 참여 인원 초과시 참여 실패")
    void participantsMax_add_throwError() {
        // given
        Participants participants = new Participants(List.of(participant1), 1);

        // when, then
        assertThrows(ExerciseMateException.class, () -> participants.add(participant2));
    }

    @Test
    @DisplayName("참여자 탈퇴 성공")
    void participantsRemove_success() {
        // given
        Participants participants = new Participants();
        participants.add(participant2);

        // when
        participants.remove(participant2);

        // then
        assertEquals(0, participants.size());
    }

    @Test
    @DisplayName("참여자가 null일때 탈퇴시 에러발생")
    void participantsRemove_nullUser_throwError() {
        // given
        Participants participants = new Participants();
        participants.add(participant1);

        // when, then
        assertThrows(ExerciseMateException.class, () -> participants.remove(null));
    }

    @Test
    @DisplayName("참여하고 있는 user가 아닐때 탈퇴시 에러발생")
    void participantsRemove_wrongUser_throwError() {
        // given
        Participants participants = new Participants();
        participants.add(participant1);

        // when, then
        assertThrows(ExerciseMateException.class, () -> participants.remove(participant2));
    }
}