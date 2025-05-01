package com.f3.exercise_mate.appointment.domain;

import lombok.Getter;

@Getter
public enum Level {
    BEGINNER("입문"),   // 입문
    NOVICE("초보"),     // 초보
    INTERMEDIATE("중수"),  // 중수
    ADVANCED("고수");   // 고수


    private final String name;

    Level(String name) {
        this.name = name;
    }

    public boolean isHigherThan(Level level) {
        return this.ordinal() > level.ordinal();
    }

    public boolean isLowerThan(Level other) {
        return this.ordinal() <= other.ordinal();
    }
}
