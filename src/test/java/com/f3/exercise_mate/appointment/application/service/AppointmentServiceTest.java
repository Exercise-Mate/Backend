package com.f3.exercise_mate.appointment.application.service;

import com.f3.exercise_mate.appointment.application.dto.CreateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.JoinAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.UpdateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.question.AnswerRequestDto;
import com.f3.exercise_mate.appointment.application.dto.question.CreateQuestionRequestDto;
import com.f3.exercise_mate.appointment.domain.*;
import com.f3.exercise_mate.appointment.repository.FakeAppointmentRepository;
import com.f3.exercise_mate.appointment.repository.FakeQuestionRepository;
import com.f3.exercise_mate.user.domain.User;
import com.f3.exercise_mate.user.repository.FakeUserRepository;
import com.f3.exercise_mate.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentServiceTest {


    private AppointmentService appointmentService;
    private FakeAppointmentRepository fakeAppointmentRepository;
    private FakeQuestionRepository fakeQuestionRepository;
    private UserService userService;
    private FakeUserRepository fakeUserRepository;

    private static String title;
    private static String description;
    private static Sport sport;
    private static Location location;
    private static DateInfo dateInfo;
    private static CreateAppointmentRequestDto dto;
    private static User user;
    private static List<CreateQuestionRequestDto> questionRequests;

    private List<CreateQuestionRequestDto> getQuestions(int size) {
        List<CreateQuestionRequestDto> list = new ArrayList<>();
        for(int i=0; i<size; i++) {
            list.add(new CreateQuestionRequestDto("this is a " + (i+1) +  "content"));
        }

        return list;
    }

    @BeforeEach
    void setUp() {
        fakeAppointmentRepository = new FakeAppointmentRepository();
        fakeUserRepository = new FakeUserRepository();
        fakeQuestionRepository = new FakeQuestionRepository();
        userService = new UserService(fakeUserRepository);
        appointmentService = new AppointmentService(fakeAppointmentRepository, fakeQuestionRepository, userService);

        user = new User(1L, 20, "test");
        fakeUserRepository.save(user);
        title = "this is a title";
        description = "this is a description";
        sport = Sport.BASEBALL;
        location = new Location("서울시 강남구", "강남야구장", null);
        dateInfo = new DateInfo(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1));
        questionRequests = getQuestions(3);
        dto = new CreateAppointmentRequestDto(title, user.getId(), description, sport, location, dateInfo, 10, questionRequests);
    }

    @Test
    @DisplayName("약속 생성시 정상적으로 생성된다.")
    void createAppointment_success() {
        // when
        Appointment saveAppointment = appointmentService.createAppointment(dto);

        // then
        Appointment appointment = appointmentService.getAppointment(saveAppointment.getId());
        assertEquals(appointment, saveAppointment);
    }

    @Test
    @DisplayName("약속 수정이 정상적으로 반영된다.")
    void updateAppointment_success() {
        // given
        Appointment appointment = appointmentService.createAppointment(dto);
        String updateTitle = "this is a update Title";
        String updateDescription = "this is a update Description";
        UpdateAppointmentRequestDto updateDto = new UpdateAppointmentRequestDto(updateTitle, user.getId(), updateDescription, sport, location, dateInfo, null);

        // when
        Appointment updateAppointment = appointmentService.updateAppointment(appointment.getId(), updateDto);

        // then
        assertEquals(updateTitle, updateAppointment.getTitle());
        assertEquals(appointment.getCreator(), updateAppointment.getCreator());
        assertEquals(updateDescription, updateAppointment.getDescription());
    }

    @Test
    @DisplayName("약속 생성시 질문도 함께 저장된다.")
    void createAppointment_withQuestions_success() {
        // given
        List<CreateQuestionRequestDto> dtos = List.of(
                new CreateQuestionRequestDto("first Question"),
                new CreateQuestionRequestDto("second Question"),
                new CreateQuestionRequestDto("third Question")
        );

        dto = new CreateAppointmentRequestDto(title, user.getId(), description, sport, location, dateInfo, 10, dtos);

        // when
        Appointment saveAppointment = appointmentService.createAppointment(dto);

        // then
        List<Question> questions = fakeQuestionRepository.findByAppointmentId(saveAppointment.getId());

        assertEquals(3, questions.size());
        assertEquals(saveAppointment.getId(), questions.get(0).getAppointment().getId());
        assertEquals("first Question", questions.get(0).getContent());
        assertEquals("second Question", questions.get(1).getContent());
        assertEquals("third Question", questions.get(2).getContent());
    }

    @Test
    @DisplayName("약속 참가신청시 정상 참여")
    void joinAppointment_success() {
        // given
        Appointment appointment = appointmentService.createAppointment(dto);
        List<AnswerRequestDto> answers = List.of(
                new AnswerRequestDto(1L, "this is first Answer"),
                new AnswerRequestDto(2L, "this is second Answer"),
                new AnswerRequestDto(3L, "this is third Answer")
        );
        User joinUser = new User(3L, 20, "join");

        JoinAppointmentRequestDto requestDto = new JoinAppointmentRequestDto(appointment.getId(), joinUser.getId(), answers);


        // when
        appointmentService.joinAppointment(requestDto);

    }


}
