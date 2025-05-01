package com.f3.exercise_mate.appointment.domain;

public class LevelRange {
    private final Level minLevel;
    private final Level maxLevel;

    public LevelRange(Level minLevel, Level maxLevel) {
        checkLevel(minLevel, maxLevel);
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    /* null, null 상관없음 */
    private void checkLevel(Level minLevel, Level maxLevel) {
        if (minLevel != null && maxLevel != null) {
            if (minLevel.ordinal() > maxLevel.ordinal()) {
                throw new IllegalArgumentException("최소 레벨은 최대 레벨보다 낮거나 같아야 합니다.");
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
