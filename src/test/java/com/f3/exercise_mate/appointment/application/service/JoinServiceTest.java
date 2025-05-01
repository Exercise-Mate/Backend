package com.f3.exercise_mate.appointment.application.service;

import com.f3.exercise_mate.appointment.application.dto.CreateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.JoinAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.UpdateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.question.AnswerRequestDto;
import com.f3.exercise_mate.appointment.application.dto.question.CreateQuestionRequestDto;
import com.f3.exercise_mate.appointment.application.exception.AppointmentException;
import com.f3.exercise_mate.appointment.domain.*;
import com.f3.exercise_mate.appointment.repository.FakeAppointmentRepository;
import com.f3.exercise_mate.appointment.repository.FakeJoinRepository;
import com.f3.exercise_mate.appointment.repository.FakeQuestionRepository;
import com.f3.exercise_mate.user.domain.User;
import com.f3.exercise_mate.user.repository.FakeUserRepository;
import com.f3.exercise_mate.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JoinServiceTest {

    private JoinService joinService;
    private UserService userService;
    private AppointmentService appointmentService;

    private FakeAppointmentRepository fakeAppointmentRepository;
    private FakeQuestionRepository fakeQuestionRepository;
    private FakeJoinRepository fakeJoinRepository;
    private FakeUserRepository fakeUserRepository;

    private JoinAppointmentRequestDto joinAppointmentRequestDto;
    private List<AnswerRequestDto> answerRequestDto;

    private static String title;
    private static String description;
    private static Sport sport;
    private static Location location;
    private static DateInfo dateInfo;
    private static CreateAppointmentRequestDto dto;
    private static User user;
    private static List<CreateQuestionRequestDto> questionRequests;
    private static Appointment appointment;

    @BeforeEach
    void setUp() {
        fakeAppointmentRepository = new FakeAppointmentRepository();
        fakeQuestionRepository = new FakeQuestionRepository();
        fakeUserRepository = new FakeUserRepository();
        fakeJoinRepository = new FakeJoinRepository();
        userService = new UserService(fakeUserRepository);
        appointmentService = new AppointmentService(fakeAppointmentRepository, fakeQuestionRepository, userService);
        joinService = new JoinService(userService, appointmentService, fakeQuestionRepository, fakeJoinRepository, fakeAppointmentRepository);

        user = new User(1L, 30, "test");
        fakeUserRepository.save(user);
        title = "this is a title";
        description = "this is a description";
        sport = Sport.BASEBALL;
        location = new Location("서울시 강남구", "강남야구장", null);
        dateInfo = new DateInfo(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1));
        questionRequests = getQuestions(3);
        dto = new CreateAppointmentRequestDto(title, user.getId(), description, sport, location, dateInfo, 10, questionRequests);

        appointment = appointmentService.createAppointment(dto);

        joinAppointmentRequestDto = new JoinAppointmentRequestDto(appointment.getId(), user.getId(), getAnswers(3));
    }

    private List<CreateQuestionRequestDto> getQuestions(int size) {
        List<CreateQuestionRequestDto> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new CreateQuestionRequestDto("this is a " + (i + 1) + "content"));
        }

        return list;
    }

    private List<AnswerRequestDto> getAnswers(int size) {
        List<AnswerRequestDto> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new AnswerRequestDto((long) i, "this is " + (i + 1) + "answer"));
        }

        return list;
    }

    @Test
    @DisplayName("약속 참가 신청에 성공하면 PENDING 상태로 생성된다.")
    void join_success() {
        // given
        User joiner = fakeUserRepository.save(new User(2L, 20, "test"));
        List<AnswerRequestDto> answerRequestDtos = getAnswers(3);
        JoinAppointmentRequestDto dto = new JoinAppointmentRequestDto(1L, joiner.getId(), answerRequestDtos);

        // when
        Join join = joinService.requestJoin(dto);

        // then
        assertEquals(JoinStatus.PENDING, join.getStatus());
        assertEquals(1L, join.getAppointment().getId());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 19, 20, 81, 90})
    @DisplayName("약속의 제한 연령에 포함되지 않는다면 에러 발생")
    void join_fail(int age) {
        // given
        User joiner = fakeUserRepository.save(new User(2L, age, "test"));
        AgeRange range = new AgeRange(21, 80);
        Appointment saved = fakeAppointmentRepository.save(
                Appointment.create(null, title, user, description, sport, location, dateInfo, 10, range));

        List<AnswerRequestDto> answerRequestDtos = getAnswers(3);

        JoinAppointmentRequestDto dto = new JoinAppointmentRequestDto(2L, joiner.getId(), answerRequestDtos);

        // when
        assertThrows(AppointmentException.class, () -> joinService.requestJoin(dto));
    }

    @Test
    @DisplayName("참가 신청 목록 중 대기 상태인 것들만 불러온다.")
    void getJoinStatusPending() {
        // given
        Appointment saved = fakeAppointmentRepository.save(
                Appointment.create(null, title, user, description, sport, location, dateInfo, 10));

        List<AnswerRequestDto> answerRequestDtos = getAnswers(3);

        User joiner1 = fakeUserRepository.save(new User(null, 20, "test"));
        User joiner2 = fakeUserRepository.save(new User(null, 20, "test"));

        JoinAppointmentRequestDto dto1 = new JoinAppointmentRequestDto(saved.getId(), joiner1.getId(), answerRequestDtos);
        JoinAppointmentRequestDto dto2 = new JoinAppointmentRequestDto(saved.getId(), joiner2.getId(), answerRequestDtos);

        // when
        Join join1 = joinService.requestJoin(dto1);
        Join join2 = joinService.requestJoin(dto2);

        join2.accepted();

        List<Join> joins = joinService.getJoins(saved.getId(), JoinStatus.PENDING);

        // then
        assertEquals(1, joins.size());
        assertEquals(JoinStatus.PENDING, joins.get(0).getStatus());
    }

    @Test
    @DisplayName("참가 신청을 수락하면 ACCEPTED 상태가 된다.")
    void join_accepted() {
        // given
        Appointment saved = fakeAppointmentRepository.save(
                Appointment.create(null, title, user, description, sport, location, dateInfo, 10));

        List<AnswerRequestDto> answerRequestDtos = getAnswers(3);

        User joiner1 = fakeUserRepository.save(new User(null, 20, "test"));

        JoinAppointmentRequestDto dto1 = new JoinAppointmentRequestDto(saved.getId(), joiner1.getId(), answerRequestDtos);

        // when
        Join join1 = joinService.requestJoin(dto1);
        joinService.approveJoin(join1.getId());

        Participants participants = saved.getParticipants();

        // then
        assertEquals(2, saved.participantsCount());
        assertEquals(joiner1, participants.getParticipants().get(1).getUser());
    }

    @Test
    @DisplayName("참가 신청을 거절하면 REJECTED 상태가 된다.")
    void join_rejected() {
        // given
        Appointment saved = fakeAppointmentRepository.save(
                Appointment.create(null, title, user, description, sport, location, dateInfo, 10));

        List<AnswerRequestDto> answerRequestDtos = getAnswers(3);

        User joiner1 = fakeUserRepository.save(new User(null, 20, "test"));

        JoinAppointmentRequestDto dto1 = new JoinAppointmentRequestDto(saved.getId(), joiner1.getId(), answerRequestDtos);

        // when
        Join join1 = joinService.requestJoin(dto1);
        joinService.rejectJoin(join1.getId());

        List<Join> joins = joinService.getJoins(saved.getId(), JoinStatus.PENDING);

        // then
        assertEquals(0, joins.size());
        assertEquals(1, saved.participantsCount());
        assertEquals(JoinStatus.REJECTED, join1.getStatus());

    }


}