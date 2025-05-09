package com.f3.exercise_mate.club.domain;

import com.f3.exercise_mate.common.exception.ErrorCode;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubJoinAnswer {

    private Long answerId;
    private Long questionId;
    private Long clubMemberId;
    private String answer;

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 200;

    public ClubJoinAnswer(Long answerId, Long questionId, Long clubMemberId, String answer) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.clubMemberId = clubMemberId;
        this.answer = answer;
    }

    public void updateAnswer(String answer) {
        this.answer = answer;
    }

    public void validate() {
        if (this.answer == null || this.answer.isEmpty()) {
            throw new ExerciseMateException(ErrorCode.CLUB_JOIN_ANSWER_REQUIRED);
        }
        if (this.answer.length() < MIN_LENGTH) {
            throw new ExerciseMateException(ErrorCode.CLUB_JOIN_ANSWER_TOO_SHORT);
        }
        if (this.answer.length() > MAX_LENGTH) {
            throw new ExerciseMateException(ErrorCode.CLUB_JOIN_ANSWER_TOO_LONG);
        }
    }
}
