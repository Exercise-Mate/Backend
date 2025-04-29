package com.f3.exercise_mate.club.repository;

import com.f3.exercise_mate.club.application.interfaces.ClubRepository;
import com.f3.exercise_mate.club.domain.Club;

import java.util.HashMap;
import java.util.Map;

public class FakeClubRepository implements ClubRepository {
    private final Map<Long, Club> store = new HashMap<>();
    private long idSequence = 1L;

    @Override
    public Club save(Club club) {
        Club newClub = Club.builder()
                .clubId(idSequence++)
                .name(club.getName())
                .sportType(club.getSportType())
                .mainArea(club.getMainArea())
                .sportAbilityMin(club.getSportAbilityMin())
                .sportAbilityMax(club.getSportAbilityMax())
                .maxMembersLimit(club.getMaxMembersLimit())
                .genderLimit(club.getGenderLimit())
                .ageLimitMin(club.getAgeLimitMin())
                .ageLimitMax(club.getAgeLimitMax())
                .imgUrl(club.getImgUrl())
                .build();

        store.put(newClub.getClubId(), newClub);
        return newClub;
    }

    public Club findById(Long clubId) {
        return store.get(clubId);
    }

    public int size() {
        return store.size();
    }
}
