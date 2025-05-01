package com.f3.exercise_mate.appointment.repository.entity;

import com.f3.exercise_mate.appointment.domain.*;
import com.f3.exercise_mate.common.repository.TimeBaseEntity;
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
public class AppointmentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity creator;

    private String imageUrl;
    private String description;

    @Embedded
    private Location location;

    @Embedded
    private DateInfo dateInfo;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    @Embedded
    private LevelRange levelRange;

    @Embedded
    private AgeRange ageRange;

    private boolean isClub;

    public static AppointmentEntity from(Appointment appointment) {
        return new AppointmentEntity(
                appointment.getId(),
                appointment.getTitle(),
                UserEntity.from(appointment.getCreator()),
                appointment.getImageUrl(),
                appointment.getDescription(),
                appointment.getLocation(),
                appointment.getDateInfo(),
                appointment.getSports(),
                appointment.getLevel(),
                appointment.getRange(),
                appointment.isClub()
        );
    }

    public Appointment toAppointment() {
        return Appointment.builder()
                .id(id)
                .title(title)
                .imageUrl(imageUrl)
                .description(description)
                .creator(creator.toUser())
                .location(location)
                .dateInfo(dateInfo)
                .sports(sport)
                .level(levelRange)
                .range(ageRange)
                .isClub(isClub)
                .build();
    }
}
