package com.f3.exercise_mate.club.application.dto;

import com.f3.exercise_mate.club.domain.Club;
import com.f3.exercise_mate.club.domain.ClubJoinAnswerList;
import com.f3.exercise_mate.common.entity.SportAbility;
import com.f3.exercise_mate.user.domain.User;

public record JoinClubRequestDto(
        Club club,
        User user,
        SportAbility sportAbility,
        ClubJoinAnswerList clubJoinAnswerList) {
}
