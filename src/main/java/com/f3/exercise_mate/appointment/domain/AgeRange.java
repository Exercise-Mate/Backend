package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AgeRange {
    private Integer minAge;
    private Integer maxAge;

    private final int MIN_AGE = 0;
    private final int MAX_AGE = 100;


    public AgeRange(Integer minAge, Integer maxAge) {
        checkAge(minAge, maxAge);
        this.minAge = minAge == null ? MIN_AGE : minAge;
        this.maxAge = maxAge == null ? MAX_AGE : maxAge;
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

    public boolean isAvaliableAge(Integer age) {
        if(minAge == null && maxAge == null) {
            return true;
        }

        if(age == null) {
            throw new ExerciseMateException(ErrorCode.AGE_RANGE_AGE_REQUIRED);
        }

        return age >= minAge && age <= maxAge;
    }

}
