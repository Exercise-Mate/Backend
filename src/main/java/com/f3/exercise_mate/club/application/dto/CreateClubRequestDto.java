package com.f3.exercise_mate.club.application.dto;

import com.f3.exercise_mate.club.domain.Club;
import com.f3.exercise_mate.club.domain.ClubJoinQuestionList;

public record CreateClubRequestDto(
        Club club,
        ClubJoinQuestionList clubJoinQuestionList) {

}
