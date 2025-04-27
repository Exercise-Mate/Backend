package com.f3.exercise_mate.appointment.application.service;

import com.f3.exercise_mate.appointment.application.dto.CreateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.UpdateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.domain.Appointment;
import com.f3.exercise_mate.appointment.domain.DateInfo;
import com.f3.exercise_mate.appointment.domain.Location;
import com.f3.exercise_mate.appointment.domain.Sport;
import com.f3.exercise_mate.appointment.repository.FakeAppointmentRepository;
import com.f3.exercise_mate.user.entity.User;
import com.f3.exercise_mate.user.repository.FakeUserRepository;
import com.f3.exercise_mate.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentServiceTest {


    private AppointmentService appointmentService;
    private FakeAppointmentRepository fakeAppointmentRepository;
    private UserService userService;
    private FakeUserRepository fakeUserRepository;

    private static String title;
    private static String description;
    private static Sport sport;
    private static Location location;
    private static DateInfo dateInfo;
    private static CreateAppointmentRequestDto dto;
    private static User user;

    @BeforeEach
    void setUp() {
        fakeAppointmentRepository = new FakeAppointmentRepository();
        fakeUserRepository = new FakeUserRepository();
        userService = new UserService(fakeUserRepository);
        appointmentService = new AppointmentService(fakeAppointmentRepository, userService);

        user = new User(1L, "test");
        fakeUserRepository.save(user);
        title = "this is a title";
        description = "this is a description";
        sport = Sport.BASEBALL;
        location = new Location("서울시 강남구", "강남야구장", null);
        dateInfo = new DateInfo(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1));
        dto = new CreateAppointmentRequestDto(title, user.getId(), description, sport, location, dateInfo);
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
    @DisplayName("약속 수정 정상적으로 반영된다.")
    void updateAppointment_success() {
        // given
        Appointment appointment = appointmentService.createAppointment(dto);
        String updateTitle = "this is a update Title";
        String updateDescription = "this is a update Description";
        UpdateAppointmentRequestDto updateDto = new UpdateAppointmentRequestDto(updateTitle, user.getId(), updateDescription, sport, location, dateInfo);

        // when
        Appointment updateAppointment = appointmentService.updateAppointment(appointment.getId(), updateDto);

        // then
        assertEquals(updateTitle, updateAppointment.getTitle());
        assertEquals(appointment.getCreator(), updateAppointment.getCreator());
        assertEquals(updateDescription, updateAppointment.getDescription());
    }
}
