package com.f3.exercise_mate.club.entity;

import com.f3.exercise_mate.club.exception.ClubException;
import com.f3.exercise_mate.club.exception.ClubErrorCode;
import com.f3.exercise_mate.common.entity.SportAbility;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Table(name = "club_info")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubId;

    private String name;

    private String description;

    private String sportType;

    private String mainArea;

    @Enumerated(EnumType.STRING)
    private SportAbility sportAbilityMin;

    @Enumerated(EnumType.STRING)
    private SportAbility sportAbilityMax;

    private Integer maxMembersLimit;

    private String genderLimit;

    private Integer ageLimitMin;

    private Integer ageLimitMax;

    private String imgUrl;

    private LocalDate createAt;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubExtraArea> extraAreas;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubJoinQuestion> joinQuestions;

    // 필수 입력 값 유효성 검사 
    public void checkCreateRequiredValue() {
        if (ObjectUtils.isEmpty(this.name)) {
            throw new ClubException(ClubErrorCode.CLUB_CREATE_NAME_NOT_FOUND);
        }
        if (ObjectUtils.isEmpty(this.sportType)) {
            throw new ClubException(ClubErrorCode.CLUB_CREATE_SPORT_TYPE_NOT_FOUND);
        }
        if (ObjectUtils.isEmpty(this.mainArea)) {
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
