package com.f3.exercise_mate.club.application.service;

import com.f3.exercise_mate.club.application.dto.CreateClubRequestDto;
import com.f3.exercise_mate.club.domain.Club;
import com.f3.exercise_mate.club.repository.FakeClubRepository;
import com.f3.exercise_mate.common.entity.SportAbility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClubServiceTest {

    private FakeClubRepository clubRepository;
    private ClubService clubService;
    private CreateClubRequestDto createClubRequestDto;

    @BeforeEach
    public void setUp() {
        clubRepository = new FakeClubRepository();
        clubService = new ClubService(clubRepository, null); // ClubJoinQuestionRepository는 null로 설정 (질문은 제외)

        createClubRequestDto = new CreateClubRequestDto(
                Club.builder()
                        .name("DM 크루")
                        .sportType("클라이밍")
                        .mainArea("서울 은평구")
                        .description("클라이밍 같이 즐겨요!")
                        .sportAbilityMin(SportAbility.BEGINNER)
                        .sportAbilityMax(SportAbility.EXPERT)
                        .build(),
                null
        );
    }

    @DisplayName("클럽을 생성하면 클럽 객체가 저장된다.")
    @Test
    void createClubTest() {
        // given && when
        Club createdClub = clubService.createClub(createClubRequestDto);

        // then
        assertEquals(1, clubRepository.size());
        Club savedClub = clubRepository.findById(1L);

        assertEquals("DM 크루", savedClub.getName());
        assertEquals("클라이밍", savedClub.getSportType());
        assertEquals("서울 은평구", savedClub.getMainArea());
        assertEquals(savedClub.getClubId(), 1L);
    }
}
