package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AgeRange {
    private Integer min;
    private Integer max;

    @Transient
    private final int MIN_AGE = 0;

    @Transient
    private final int MAX_AGE = 100;


    public AgeRange(Integer min, Integer max) {
        checkAge(min, max);
        this.min = min == null ? MIN_AGE : min;
        this.max = max == null ? MAX_AGE : max;
    }

    /*
    * 무관 설정시 나이는 null, null로 설정 됨.
    * */
    public static AgeRange unrestricted() {
        return new AgeRange(null, null);
    }

    private void checkAge(Integer min, Integer max) {
        if(min != null && max != null) {
            if (min < 0 || max < 0) {
                throw new ExerciseMateException(ErrorCode.AGE_RANGE_NEGATIVE);
            }

            if (min > max) {
                throw new ExerciseMateException(ErrorCode.AGE_RANGE_MIN_OVER_MAX);
            }

            if (max > MAX_AGE) {
                throw new ExerciseMateException(ErrorCode.AGE_RANGE_EXCEED_MAX);
            }
        }
    }

    public boolean isAvailableAge(Integer age) {
        if(min == null && max == null) {
            return true;
        }

        if(age == null) {
            throw new ExerciseMateException(ErrorCode.AGE_RANGE_AGE_REQUIRED);
        }

        return age >= min && age <= max;
    }

}
