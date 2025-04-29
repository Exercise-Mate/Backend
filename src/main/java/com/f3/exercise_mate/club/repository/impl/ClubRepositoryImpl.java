package com.f3.exercise_mate.club.repository.impl;

import com.f3.exercise_mate.club.application.interfaces.ClubRepository;
import com.f3.exercise_mate.club.domain.Club;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepository {
    @Override
    public Club save(Club club) {
        return null;
    }

    @Override
    public Club findById(Long id) {
        return null;
    }
}
