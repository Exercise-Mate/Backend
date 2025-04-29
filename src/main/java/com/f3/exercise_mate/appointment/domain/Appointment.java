package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.appointment.application.dto.UpdateAppointmentRequestDto;
import com.f3.exercise_mate.appointment.application.dto.question.CreateQuestionRequestDto;
import com.f3.exercise_mate.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Appointment {
    private Long id;
    private String title;
    private User creator;
    private String imageUrl;
    private String description;
    private Location location;
    private DateInfo dateInfo;
    private Participants participants;
    private Sport sports;
    private LevelRange level;
    private AgeRange range;
    private boolean isClub;

    public static Appointment create(Long id, String title, User creator, String description, Sport sport, Location location, DateInfo dateInfo, int maxParticipant) {
        Appointment appointment = Appointment.builder()
                .id(id)
                .title(title)
                .creator(creator)
                .description(description)
                .sports(sport)
                .location(location)
                .dateInfo(dateInfo)
                .participants(Participants.defaultCreate(null, maxParticipant))
                .range(AgeRange.unrestricted())
                .isClub(false)
                .build();

        appointment.join(creator);
        appointment.validate();
        return appointment;
    }

    public static Appointment create(Long id, String title, User creator, String description, Sport sport, Location location, DateInfo dateInfo, int maxParticipant, AgeRange range) {
        Appointment appointment = Appointment.builder()
                .id(id)
                .title(title)
                .creator(creator)
                .description(description)
                .sports(sport)
                .location(location)
                .dateInfo(dateInfo)
                .participants(Participants.defaultCreate(null, maxParticipant))
                .range(range)
                .isClub(false)
                .build();

        appointment.join(creator);
        appointment.validate();
        return appointment;
    }

    public int participantsCount() {
        return participants.size();
    }

    public boolean isAvailableAge(int age) {
        return range.isAvaliableAge(age);
    }

    public void join(User user) {
        participants.add(new Participant(user, this));
    }

    public void exit(User user) {
        participants.remove(new Participant(user, this));
    }

    private void validate() {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("약속의 제목은 필수입니다,");
        }

        if (title.length() < 5 || title.length() > 100) {
            throw new IllegalArgumentException("약속의 제목은 5~100자로 입력해야합니다.");
        }

        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("약속의 설명은 필수입니다.");
        }

        if (description.length() < 5 || description.length() > 500) {
            throw new IllegalArgumentException("약속의 설명은 5~500자로 입력해야합니다.");
        }

    }

    public void updateAppointment(User user, UpdateAppointmentRequestDto dto) {
        if(!user.equals(creator)) {
            throw new IllegalArgumentException("약속 생성자만 수정 할 수 있습니다");
        }

        this.title = dto.title();
        this.description = dto.description();
        this.location = dto.location();
        this.dateInfo = dto.dateInfo();
        this.sports = dto.sport();
        validate();
    }

}
