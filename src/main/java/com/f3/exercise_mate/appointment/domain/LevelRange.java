package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.common.entity.SportAbility;
import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LevelRange {
    private SportAbility minLevel;
    private SportAbility maxLevel;

    public LevelRange(SportAbility minLevel, SportAbility maxLevel) {
        checkLevel(minLevel, maxLevel);
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    /* null, null 상관없음 */
    private void checkLevel(SportAbility minLevel, SportAbility maxLevel) {
        if (minLevel != null && maxLevel != null) {
            if (minLevel.ordinal() > maxLevel.ordinal()) {
                throw new ExerciseMateException(ErrorCode.LEVEL_RANGE_INVALID);
            }
        }
    }

    public static LevelRange unrestricted() {
        return new LevelRange(null, null);
    }

    public SportAbility getMinLevel() {
        return minLevel;
    }

    public SportAbility getMaxLevel() {
        return maxLevel;
    }
}
