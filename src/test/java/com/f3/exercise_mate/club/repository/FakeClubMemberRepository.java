package com.f3.exercise_mate.club.repository;

import com.f3.exercise_mate.club.application.interfaces.ClubMemberRepository;
import com.f3.exercise_mate.club.domain.ClubMember;
import java.util.HashMap;
import java.util.Map;

public class FakeClubMemberRepository implements ClubMemberRepository {
    private final Map<Long, ClubMember> store = new HashMap<>();
    private long idSequence = 1L;

    @Override
    public ClubMember save(ClubMember clubMember) {
        if (clubMember.getClubMemberId() == null) {
            clubMember = new ClubMember(idSequence++, clubMember.getMember(), clubMember.getClub(),
                    clubMember.getStatus(), clubMember.getSportAbility(), clubMember.getGrade());
        }
        store.put(clubMember.getClubMemberId(), clubMember);
        return clubMember;
    }

    @Override
    public ClubMember findByClubIdAndUserId(Long clubId, Long userId) {
        return store.values().stream()
                .filter(member -> member.getClub().getClubId().equals(clubId) && member.getMember().getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public ClubMember findByClubMemberId(Long clubMemberId) {
        return store.get(clubMemberId);
    }

    public int size() {
        return store.size();
    }
}
