package com.f3.exercise_mate.club.application.interfaces;

import com.f3.exercise_mate.club.domain.ClubMember;

public interface ClubMemberRepository {
    ClubMember save(ClubMember clubMember);
    ClubMember findByClubIdAndUserId(Long clubId, Long userId);
    ClubMember findByClubMemberId(Long clubMemberId);

}
