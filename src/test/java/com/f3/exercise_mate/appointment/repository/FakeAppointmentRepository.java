package com.f3.exercise_mate.appointment.repository;

import com.f3.exercise_mate.appointment.application.interfaces.AppointmentRepository;
import com.f3.exercise_mate.appointment.domain.Appointment;

import java.util.HashMap;
import java.util.Map;

public class FakeAppointmentRepository implements AppointmentRepository {

    private final Map<Long, Appointment> store = new HashMap<>();

    @Override
    public Appointment save(Appointment appointment) {
        if (appointment.getId() != null) {
            store.put(appointment.getId(), appointment);
            return appointment;
        }
        long id = store.size() + 1;
        Appointment newAppointment = Appointment.create(id, appointment.getTitle(), appointment.getCreator(), appointment.getDescription(), appointment.getSports(), appointment.getLocation(), appointment.getDateInfo(), 10, appointment.getRange());
        store.put(id, newAppointment);
        return store.get(id);
    }

    @Override
    public Appointment findById(Long id) {
        return store.get(id);
    }
}
