package com.f3.exercise_mate.club.domain;

import com.f3.exercise_mate.appointment.domain.JoinStatus;
import com.f3.exercise_mate.common.entity.SportAbility;
import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import com.f3.exercise_mate.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubMember {
    private Long clubMemberId;
    private User member;
    private Club club;
    private JoinStatus status;
    private SportAbility sportAbility;
    private ClubGrade grade;

    public ClubMember(Long clubMemberId, User member, Club club, JoinStatus status, SportAbility sportAbility,
                      ClubGrade grade) {
        validate();
        this.clubMemberId = clubMemberId;
        this.member = member;
        this.club = club;
        this.status = status;
        this.sportAbility = sportAbility;
        this.grade = grade;
    }

    public void accept() {
        this.status = JoinStatus.ACCEPTED;
    }

    public void reject() {
        this.status = JoinStatus.REJECTED;
    }

    public void changeGrade(ClubGrade grade) {
        this.grade = grade;
    }

    public void validate() {
        if (!sportAbility.isInRange(this.club)) {
            throw new ExerciseMateException(ErrorCode.CLUB_JOIN_SPORT_ABILITY_NOT_IN_RANGE);
        }
        if (!member.isInRange(this.club)) {
            throw new ExerciseMateException(ErrorCode.CLUB_JOIN_AGE_NOT_IN_RANGE);
        }
        // 성별 제한 추가 예정
    }

    public void validateAlreadyJoined() {
        if (this.status == JoinStatus.PENDING) {
            throw new ExerciseMateException(ErrorCode.CLUB_MEMBER_ALREADY_PENDING);
        }
        if (this.status == JoinStatus.ACCEPTED) {
            throw new ExerciseMateException(ErrorCode.CLUB_MEMBER_ALREADY_EXISTS);
        }
    }
}
