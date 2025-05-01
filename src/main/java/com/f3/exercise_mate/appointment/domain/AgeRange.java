package com.f3.exercise_mate.appointment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
                throw new IllegalArgumentException("나이는 0보다 작을 수 없습니다.");
            }

            if (min > max) {
                throw new IllegalArgumentException("최소 나이는 최대 나이보다 작아야 합니다");
            }

            if (max > MAX_AGE) {
                throw new IllegalArgumentException("최대 나이는 99세 입니다.");
            }
        }
    }

    public boolean isAvaliableAge(Integer age) {
        if(minAge == null && maxAge == null) {
            return true;
        }

        if(age == null) {
            throw new IllegalArgumentException("올바른 나이를 입력하세요");
        }

        return age >= minAge && age <= maxAge;
    }

}
