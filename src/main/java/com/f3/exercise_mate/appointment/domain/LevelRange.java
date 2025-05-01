package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.appointment.application.exception.AppointmentErrorCode;
import com.f3.exercise_mate.appointment.application.exception.AppointmentException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LevelRange {
    private Level minLevel;
    private Level maxLevel;

    public LevelRange(Level minLevel, Level maxLevel) {
        checkLevel(minLevel, maxLevel);
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    /* null, null 상관없음 */
    private void checkLevel(Level minLevel, Level maxLevel) {
        if (minLevel != null && maxLevel != null) {
            if (minLevel.ordinal() > maxLevel.ordinal()) {
                throw new AppointmentException(AppointmentErrorCode.LEVEL_RANGE_INVALID);
            }
        }
    }

    public static LevelRange unrestricted() {
        return new LevelRange(null, null);
    }

    public Level getMinLevel() {
        return minLevel;
    }

    public Level getMaxLevel() {
        return maxLevel;
    }
}
