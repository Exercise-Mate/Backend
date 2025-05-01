package com.f3.exercise_mate.club.domain;

import com.f3.exercise_mate.club.exception.ClubErrorCode;
import com.f3.exercise_mate.club.exception.ClubException;
import com.f3.exercise_mate.common.entity.SportAbility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClubTest {

    @DisplayName("클럽 생성 시 클럽명이 없으면 예외가 발생한다.")
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
        assertThatThrownBy(() -> Club.create(invalidClub))
                .isInstanceOf(ClubException.class)
                .hasMessageContaining(ClubErrorCode.CLUB_CREATE_NAME_NOT_FOUND.getErrorMessage());
    }

    @DisplayName("클럽 생성 시 운동 종목이 없으면 예외가 발생한다.")
    @Test
    void createClubExceptionSportTypeMissing() {
        // given
        Club invalidClub = Club.builder()
                .name("DM 크루")
                .mainArea("서울 은평구")
                .sportAbilityMin(SportAbility.BEGINNER)
                .sportAbilityMax(SportAbility.EXPERT)
                .description("클라이밍 같이 즐겨요!")
                .build();

        // when & then
        assertThatThrownBy(() -> Club.create(invalidClub))
                .isInstanceOf(ClubException.class)
                .hasMessageContaining(ClubErrorCode.CLUB_CREATE_SPORT_TYPE_NOT_FOUND.getErrorMessage());
    }

    @DisplayName("클럽 생성 시 주 활동 지역이 없으면 예외가 발생한다.")
    @Test
    void createClubExceptionMainAreaMissing() {
        // given
        Club invalidClub = Club.builder()
                .name("DM 크루")
                .sportType("클라이밍")
                .sportAbilityMin(SportAbility.BEGINNER)
                .sportAbilityMax(SportAbility.EXPERT)
                .description("클라이밍 같이 즐겨요!")
                .build();

        // when & then
        assertThatThrownBy(() -> Club.create(invalidClub))
                .isInstanceOf(ClubException.class)
                .hasMessageContaining(ClubErrorCode.CLUB_CREATE_MAIN_AREA_NOT_FOUND.getErrorMessage());
    }

    @DisplayName("클럽 생성 시 운동 실력 최소값이 없으면 예외가 발생한다.")
    @Test
    void createClubExceptionSportAbilityMinMissing() {
        // given
        Club invalidClub = Club.builder()
                .name("DM 크루")
                .sportType("클라이밍")
                .mainArea("서울 은평구")
                .sportAbilityMax(SportAbility.EXPERT)
                .description("클라이밍 같이 즐겨요!")
                .build();

        // when & then
        assertThatThrownBy(() -> Club.create(invalidClub))
                .isInstanceOf(ClubException.class)
                .hasMessageContaining(ClubErrorCode.CLUB_CREATE_SPORT_ABILITY_MIN_NOT_FOUND.getErrorMessage());
    }

    @DisplayName("클럽 생성 시 운동 실력 최대값이 없으면 예외가 발생한다.")
    @Test
    void createClubExceptionSportAbilityMaxMissing() {
        // given
        Club invalidClub = Club.builder()
                .name("DM 크루")
                .sportType("클라이밍")
                .mainArea("서울 은평구")
                .sportAbilityMin(SportAbility.BEGINNER)
                .description("클라이밍 같이 즐겨요!")
                .build();

        // when & then
        assertThatThrownBy(() -> Club.create(invalidClub))
                .isInstanceOf(ClubException.class)
                .hasMessageContaining(ClubErrorCode.CLUB_CREATE_SPORT_ABILITY_MAX_NOT_FOUND.getErrorMessage());
    }

    @DisplayName("클럽 생성 시 클럽 소개가 없으면 예외가 발생한다.")
    @Test
    void createClubExceptionDescriptionMissing() {
        // given
        Club invalidClub = Club.builder()
                .name("DM 크루")
                .sportType("클라이밍")
                .mainArea("서울 은평구")
                .sportAbilityMin(SportAbility.BEGINNER)
                .sportAbilityMax(SportAbility.EXPERT)
                .build();

        // when & then
        assertThatThrownBy(() -> Club.create(invalidClub))
                .isInstanceOf(ClubException.class)
                .hasMessageContaining(ClubErrorCode.CLUB_CREATE_DESCRIPTION_NOT_FOUND.getErrorMessage());
    }

    @DisplayName("클럽 생성 시 모든 필수값이 제공되면 클럽이 정상적으로 생성된다.")
    @Test
    void createClubSuccess() {
        // given
        Club validClub = Club.builder()
                .name("DM 크루")
                .sportType("클라이밍")
                .mainArea("서울 은평구")
                .sportAbilityMin(SportAbility.BEGINNER)
                .sportAbilityMax(SportAbility.EXPERT)
                .description("클라이밍 같이 즐겨요!")
                .build();

        // when
        Club createdClub = Club.create(validClub);

        // then
        assertEquals(validClub.getName(), createdClub.getName());
        assertEquals(validClub.getSportType(), createdClub.getSportType());
        assertEquals(validClub.getMainArea(), createdClub.getMainArea());
        assertEquals(validClub.getDescription(), createdClub.getDescription());
    }
}
