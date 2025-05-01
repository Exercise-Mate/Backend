package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Join {
    private Long id;
    private Appointment appointment;
    private User user;
    private List<Answer> answers;
    private JoinStatus status;

    public Join(Appointment appointment, User user, List<Answer> answers) {
        this.appointment = appointment;
        this.user = user;
        this.answers = answers;
        this.status = JoinStatus.PENDING;
    }

    public Join(Appointment appointment, User user, List<Answer> answers, JoinStatus status) {
        this.appointment = appointment;
        this.user = user;
        this.answers = answers;
        this.status = status;
    }

    public void accepted() {
        this.status = JoinStatus.ACCEPTED;
    }

    public void rejected() {
        this.status = JoinStatus.REJECTED;
    }
}
