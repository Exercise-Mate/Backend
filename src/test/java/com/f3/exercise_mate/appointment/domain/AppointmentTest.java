package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private String title = "this is a title";
    private String description = "this is a description";
    private User creator = new User("tester");
    private Sport sport = Sport.BASEBALL;
    private Location location = Location.builder()
            .address("서울시 강남구")
            .name("약속장소")
            .build();
    private DateInfo dateInfo = new DateInfo(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1));


    @Test
    @DisplayName("약속이 정상적으로 생성된다")
    void createAppointment_success() {
        // given
        Appointment appointment = Appointment.create(1L, title, creator, description, sport, location, dateInfo);

        // when, then
        assertEquals(title, appointment.getTitle());
        assertEquals(description, appointment.getDescription());
        assertEquals(creator, appointment.getCreator());
        assertEquals(sport, appointment.getSports());
        assertEquals(dateInfo, appointment.getDateInfo());
        assertEquals(location, appointment.getLocation());
        assertEquals(1, appointment.participantsCount());
    }

    @Test
    @DisplayName("약속의 제목이 최소 길이보다 짧으면 에러발생")
    void titleLengthUnder_createAppointment_throwError() {
        // given
        String underTitle = "a".repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> Appointment.create(1L, underTitle, creator, description, sport, location, dateInfo));
    }

    @Test
    @DisplayName("약속의 제목이 최대 길이보다 길면 에러발생")
    void titleLengthOver_createAppointment_throwError() {
        // given
        String overTitle = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> Appointment.create(1L, overTitle, creator, description, sport, location, dateInfo));
    }

    @Test
    @DisplayName("약속의 설명이 최소 길이보다 짧으면 에러발생")
    void descriptionLengthUnder_createAppointment_throwError() {
        // given
        String underDescription = "a".repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> Appointment.create(1L, title, creator, underDescription, sport, location, dateInfo));
    }

    @Test
    @DisplayName("약속의 설명이 최대 길이보다 길면 에러발생")
    void descriptionLengthOver_createAppointment_throwError() {
        // given
        String overDescription = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> Appointment.create(1L, title, creator, overDescription, sport, location, dateInfo));
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("약속의 제목이 null 이거나 Empty이면 에러발생")
    void titleIsNull_createAppointment_throwError(String value) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> Appointment.create(1L, value, creator, description, sport, location, dateInfo));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("약속의 제목이 null 이거나 Empty이면 에러발생")
    void setDescriptionIsNull_createAppointment_throwError(String value) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> Appointment.create(1L, title, creator, value, sport, location, dateInfo));
    }

    @Test
    @DisplayName("약속 생성자가 참가를 할시 에러발생")
    void creatorJoinAppointment_throwError() {
        // given
        Appointment appointment = Appointment.create(1L, title, creator, description, sport, location, dateInfo);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> appointment.join(creator));
    }

    @DisplayName("참여자가 탈퇴하면 Participants에서 제거된다")
    @Test
    void leaveParticipant_success() {
        // given
        User creator = new User("creator");
        Appointment appointment = Appointment.create(1L, title, creator, description, sport, location, dateInfo);
        User participant = new User("participant");
        appointment.join(participant);

        // when
        appointment.exit(participant);

        // then
        assertEquals(1, appointment.participantsCount());
    }

    @DisplayName("참가하지 않은 사람이 탈퇴하려고 하면 에러 발생")
    @Test
    void leaveParticipant_notJoinedUser_throwError() {
        // given
        User creator = new User("creator");
        Appointment appointment = Appointment.create(1L, title, creator, description, sport, location, dateInfo);
        User stranger = new User("stranger");

        // when, then
        assertThrows(IllegalArgumentException.class, () -> appointment.exit(stranger));
    }
}