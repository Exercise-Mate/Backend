package com.f3.exercise_mate.club.service;

import com.f3.exercise_mate.club.entity.Club;
import com.f3.exercise_mate.club.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Club createClub(Club club) {
        club.checkCreateRequiredValue();
        return clubRepository.save(club);
    }
}

