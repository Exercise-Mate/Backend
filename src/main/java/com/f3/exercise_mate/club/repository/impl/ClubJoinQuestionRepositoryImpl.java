package com.f3.exercise_mate.club.repository.impl;

import com.f3.exercise_mate.club.application.interfaces.ClubJoinQuestionRepository;
import com.f3.exercise_mate.club.domain.ClubJoinQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClubJoinQuestionRepositoryImpl implements ClubJoinQuestionRepository {
    @Override
    public ClubJoinQuestion save(ClubJoinQuestion clubJoinQuestion) {
        return null;
    }

    @Override
    public ClubJoinQuestion findById(Long id) {
        return null;
    }
}
