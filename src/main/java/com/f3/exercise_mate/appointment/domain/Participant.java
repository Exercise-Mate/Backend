package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    private User user;
    private Appointment appointment;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(user, that.user) && Objects.equals(appointment, that.appointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, appointment);
    }
}
