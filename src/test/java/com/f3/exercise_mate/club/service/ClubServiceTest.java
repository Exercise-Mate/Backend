package com.f3.exercise_mate.club.service;

import com.f3.exercise_mate.club.entity.Club;
import com.f3.exercise_mate.club.exception.ClubErrorCode;
import com.f3.exercise_mate.club.exception.ClubException;
import com.f3.exercise_mate.common.entity.SportAbility;
import com.f3.exercise_mate.club.repository.ClubRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClubServiceTest {

    @Mock
    private ClubRepository clubRepository;

    @InjectMocks
    private ClubService clubService;

    private Club club;

    @BeforeEach
    public void setUp() {
        club = Club.builder()
                .name("DM 크루")
                .sportType("클라이밍")
                .mainArea("서울 은평구")
                .sportAbilityMin(SportAbility.BEGINNER)
                .sportAbilityMax(SportAbility.EXPERT)
                .description("클라이밍 같이 즐겨요!")
                .build();
    }

    @DisplayName("모든 데이터가 제공되면 클럽이 성공적으로 생성된다.")
    @Test
    void createClub() {
        // given
        when(clubRepository.save(any(Club.class))).thenReturn(club);

        // when
        Club createdClub = clubService.createClub(club);

        // then
        Assertions.assertThat(createdClub).isNotNull();
        Assertions.assertThat(createdClub.getName()).isEqualTo("DM 크루");
    }

    @DisplayName("클럽명 데이터가 누락되면 클럽 생성에 실패한다.")
    @Test
    void createClubExceptionNameMissing() {
        // given
        Club invalidClub = Club.builder()
                .sportType("클라이밍")
                .mainArea("서울 은평구")
                .sportAbilityMin(SportAbility.BEGINNER)
                .sportAbilityMax(SportAbility.EXPERT)
                .description("클라이밍 같이 즐겨요!")
                .build();

        // when & then
        assertThatThrownBy(() -> clubService.createClub(invalidClub))
                .isInstanceOf(ClubException.class)
                .hasMessageContaining(ClubErrorCode.CLUB_CREATE_NAME_NOT_FOUND.getErrorMessage());
    }

}
