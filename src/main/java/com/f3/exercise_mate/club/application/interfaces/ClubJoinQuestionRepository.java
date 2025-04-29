package com.f3.exercise_mate.club.application.interfaces;

import com.f3.exercise_mate.club.domain.ClubJoinQuestion;

public interface ClubJoinQuestionRepository {
    ClubJoinQuestion save(ClubJoinQuestion clubJoinQuestion);
    ClubJoinQuestion findById(Long id);
}
