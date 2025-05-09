package com.f3.exercise_mate.club.repository.impl;

import com.f3.exercise_mate.club.application.interfaces.ClubMemberRepository;
import com.f3.exercise_mate.club.domain.ClubMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClubMemberRepositoryImpl implements ClubMemberRepository {
    @Override
    public ClubMember save(ClubMember clubMember) {
        return null;
    }

    @Override
    public ClubMember findByClubMemberId(Long clubMemberId) {
        return null;
    }

    @Override
    public ClubMember findByClubIdAndUserId(Long clubId, Long userId) {
        return null;
    }
}
