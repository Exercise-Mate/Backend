package com.f3.exercise_mate.club.application.service;

import com.f3.exercise_mate.club.application.dto.CreateClubRequestDto;
import com.f3.exercise_mate.club.application.interfaces.ClubJoinQuestionRepository;
import com.f3.exercise_mate.club.application.interfaces.ClubRepository;
import com.f3.exercise_mate.club.domain.Club;
import com.f3.exercise_mate.club.domain.ClubJoinQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubJoinQuestionRepository clubJoinQuestionRepository;

    @Transactional
    public Club createClub(CreateClubRequestDto dto) {
        Club club = Club.create(dto.club());
        clubRepository.save(club);

        if(dto.clubJoinQuestionList() != null) {
            for (ClubJoinQuestion clubJoinQuestion : dto.clubJoinQuestionList().getClubJoinQuestionsList()) {
                clubJoinQuestionRepository.save(clubJoinQuestion);
            }
        }

        return club;
    }



}

