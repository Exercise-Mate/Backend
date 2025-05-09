package com.f3.exercise_mate.club.repository.impl;

import com.f3.exercise_mate.club.application.interfaces.ClubJoinAnswerRepository;
import com.f3.exercise_mate.club.domain.ClubJoinAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClubJoinAnswerRepositoryImpl implements ClubJoinAnswerRepository {
    @Override
    public ClubJoinAnswer save(ClubJoinAnswer clubJoinAnswer) {
        return null;
    }
}
