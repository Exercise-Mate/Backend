package com.f3.exercise_mate.club.application.interfaces;

import com.f3.exercise_mate.club.domain.Club;

public interface ClubRepository {
    Club save(Club club);
    Club findById(Long id);
}
