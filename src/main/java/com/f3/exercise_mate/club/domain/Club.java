package com.f3.exercise_mate.club.domain;

import com.f3.exercise_mate.club.exception.ClubException;
import com.f3.exercise_mate.club.exception.ClubErrorCode;
import com.f3.exercise_mate.common.entity.SportAbility;
import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class Club {

    private Long clubId;
    private String name;
    private String description;
    private String sportType;
    private String mainArea;
    private SportAbility sportAbilityMin;
    private SportAbility sportAbilityMax;
    private Integer maxMembersLimit;
    private String genderLimit;
    private Integer ageLimitMin;
    private Integer ageLimitMax;
    private String imgUrl;

    public static Club create(Club club) {
        Club newClub = Club.builder()
                .clubId(club.clubId)
                .name(club.name)
                .description(club.description)
                .sportType(club.sportType)
                .mainArea(club.mainArea)
                .sportAbilityMin(club.sportAbilityMin)
                .sportAbilityMax(club.sportAbilityMax)
                .maxMembersLimit(club.maxMembersLimit)
                .genderLimit(club.genderLimit)
                .ageLimitMin(club.ageLimitMin)
                .ageLimitMax(club.ageLimitMax)
                .imgUrl(club.imgUrl)
                .build();

        newClub.checkCreateRequiredValue();
        return newClub;
    }

    private void checkCreateRequiredValue() {
        if (this.name == null || this.name.isEmpty()) {
            throw new ClubException(ClubErrorCode.CLUB_CREATE_NAME_NOT_FOUND);
        }
        if (this.description == null || this.description.isEmpty()) {
            throw new ClubException(ClubErrorCode.CLUB_CREATE_DESCRIPTION_NOT_FOUND);
        }
        if (this.sportType == null || this.sportType.isEmpty()) {
            throw new ClubException(ClubErrorCode.CLUB_CREATE_SPORT_TYPE_NOT_FOUND);
        }
        if (this.mainArea == null || this.mainArea.isEmpty()) {
            throw new ClubException(ClubErrorCode.CLUB_CREATE_MAIN_AREA_NOT_FOUND);
        }
        if (this.sportAbilityMin == null) {
            throw new ClubException(ClubErrorCode.CLUB_CREATE_SPORT_ABILITY_MIN_NOT_FOUND);
        }
        if (this.sportAbilityMax == null) {
            throw new ClubException(ClubErrorCode.CLUB_CREATE_SPORT_ABILITY_MAX_NOT_FOUND);
        }
    }
}
