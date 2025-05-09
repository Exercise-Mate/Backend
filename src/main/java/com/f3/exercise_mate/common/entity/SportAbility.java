package com.f3.exercise_mate.common.entity;

import com.f3.exercise_mate.appointment.domain.Level;
import com.f3.exercise_mate.club.domain.Club;

public enum SportAbility {
    BEGINNER("입문"),
    INTERMEDIATE("초보"),
    ADVANCED("중급"),
    EXPERT("상급");

    private final String label;

    SportAbility(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public boolean isInRange(Club club) {
        return this.ordinal() >= club.getSportAbilityMin().ordinal() && this.ordinal() <= club.getSportAbilityMax().ordinal();
    }

    public boolean isHigherThan(Level level) {
        return this.ordinal() > level.ordinal();
    }

    public boolean isLowerThan(Level other) {
        return this.ordinal() <= other.ordinal();
    }
}
