package com.f3.exercise_mate.appointment.repository.entity;

import com.f3.exercise_mate.appointment.domain.Participant;
import com.f3.exercise_mate.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    public Participant toParticipant() {
        return new Participant(user.toUser()); // appointment는 나중에 Participants 조립할 때 넣을 수도 있음
    }

    public static ParticipantEntity from(Participant participant) {
        return new ParticipantEntity(
                null,
                UserEntity.from(participant.getUser()),
                participant.getAppointment().getId()
        );
    }


}
