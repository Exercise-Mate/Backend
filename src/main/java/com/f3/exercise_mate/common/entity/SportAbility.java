package com.f3.exercise_mate.common.entity;

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
}
