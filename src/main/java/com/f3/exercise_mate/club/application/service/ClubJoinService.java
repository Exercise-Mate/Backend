package com.f3.exercise_mate.club.application.service;

import com.f3.exercise_mate.appointment.domain.JoinStatus;
import com.f3.exercise_mate.club.application.dto.JoinClubRequestDto;
import com.f3.exercise_mate.club.application.dto.UpdateClubRequestDto;
import com.f3.exercise_mate.club.application.interfaces.ClubJoinAnswerRepository;
import com.f3.exercise_mate.club.application.interfaces.ClubMemberRepository;
import com.f3.exercise_mate.club.domain.Club;
import com.f3.exercise_mate.club.domain.ClubGrade;
import com.f3.exercise_mate.club.domain.ClubJoinAnswer;
import com.f3.exercise_mate.club.domain.ClubJoinAnswerList;
import com.f3.exercise_mate.club.domain.ClubMember;
import com.f3.exercise_mate.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClubJoinService {
    private final ClubJoinAnswerRepository clubJoinAnswerRepository;
    private final ClubMemberRepository clubMemberRepository;

    @Transactional
    public ClubMember joinClub(JoinClubRequestDto dto) {
        Club club = dto.club();
        User user = dto.user();

        ClubMember member = clubMemberRepository.findByClubIdAndUserId(club.getClubId(), user.getId());
        if(member != null) {
            member.validateAlreadyJoined();
        }
        ClubMember newClubMember = new ClubMember(null, user, club, JoinStatus.PENDING, dto.sportAbility(), ClubGrade.CLUB_MEMBER);
        saveJoinAnswer(dto.clubJoinAnswerList());

        return clubMemberRepository.save(newClubMember);
    }

    public void approveJoinClub(Long clubMemberId, ClubGrade grade) {
        ClubMember clubMember = clubMemberRepository.findByClubMemberId(clubMemberId);
        if (clubMember != null) {
            clubMember.accept();
            clubMember.changeGrade(grade);
            clubMemberRepository.save(clubMember);
        }
    }

    public void rejectJoinClub(Long clubMemberId) {
        ClubMember clubMember = clubMemberRepository.findByClubMemberId(clubMemberId);
        if (clubMember != null) {
            clubMember.reject();
            clubMemberRepository.save(clubMember);
        }
    }

    @Transactional
    public void saveJoinAnswer(ClubJoinAnswerList clubJoinAnswerList) {
        for(ClubJoinAnswer answer : clubJoinAnswerList.getClubJoinAnswersList()) {
            answer.validate();
            clubJoinAnswerRepository.save(answer);
        }
    }

    @Transactional
    public void updateJoinAnswer(UpdateClubRequestDto dto) {
        for(ClubJoinAnswer answer : dto.clubJoinAnswerList().getClubJoinAnswersList()) {
            answer.validate();
            clubJoinAnswerRepository.save(answer);
        }
    }
}
