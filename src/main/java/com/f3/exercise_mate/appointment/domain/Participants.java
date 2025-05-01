package com.f3.exercise_mate.appointment.domain;


import com.f3.exercise_mate.appointment.application.exception.AppointmentErrorCode;
import com.f3.exercise_mate.appointment.application.exception.AppointmentException;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<Participant> users;
    private final int MAX_PARTICIPANTS;

    public Participants() {
        this.users = new ArrayList<>();
        MAX_PARTICIPANTS = 20;
    }

    public static Participants defaultCreate(List<Participant> users, int maxParticipants) {
        return new Participants(users, maxParticipants);
    }

    public Participants(List<Participant> users, int maxParticipants) {
        this.users = users == null ? new ArrayList<>() : new ArrayList<>(users);
        this.MAX_PARTICIPANTS = maxParticipants;
    }

    public void add(Participant user) {
        if(users.size() >= MAX_PARTICIPANTS) {
            throw new AppointmentException(AppointmentErrorCode.PARTICIPANT_OVERFLOW);
        }

        if(user == null) {
            throw new AppointmentException(AppointmentErrorCode.PARTICIPANT_NULL);
        }

        if(users.contains(user)) {
            throw new AppointmentException(AppointmentErrorCode.PARTICIPANT_DUPLICATE);
        }

        users.add(user);
    }

    public void remove(Participant user) {
        if(user == null) {
            throw new AppointmentException(AppointmentErrorCode.PARTICIPANT_NULL);
        }

        if(!users.contains(user)) {
            throw new AppointmentException(AppointmentErrorCode.PARTICIPANT_NOT_FOUND);
        }

        users.remove(user);
    }

    public int size() {
        return users.size();
    }

    public List<Participant> getParticipants() {
        return users;
    }

}
