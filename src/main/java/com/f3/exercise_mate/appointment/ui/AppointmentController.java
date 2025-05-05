package com.f3.exercise_mate.appointment.ui;

import com.f3.exercise_mate.appointment.application.dto.CreateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.UpdateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.service.AppointmentService;
import com.f3.exercise_mate.appointment.domain.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<Appointment> create(@RequestBody CreateAppointmentRequestDto request) {

        Appointment appointment = appointmentService.createAppointment(request);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/update/{appointmentId}")
    public ResponseEntity<Appointment> update(@PathVariable(name = "appointmentId") Long appointmentId, @RequestBody UpdateAppointmentRequestDto request) {
        Appointment appointment = appointmentService.updateAppointment(appointmentId, request);

        return ResponseEntity.ok(appointment);
    }

}
