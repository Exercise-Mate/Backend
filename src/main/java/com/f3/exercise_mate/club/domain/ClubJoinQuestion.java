package com.f3.exercise_mate.club.domain;

import com.f3.exercise_mate.club.exception.ClubQuestionErrorCode;
import com.f3.exercise_mate.club.exception.ClubQuestionException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubJoinQuestion {

    private Long questionId;
    private Long clubId;
    private String question;

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 200;

    public ClubJoinQuestion(Long questionId, Long clubId, String question) {
        this.questionId = questionId;
        this.clubId = clubId;
        this.question = question;

        validate(question);
    }

    public void validate(String question){
        if (question.length() < MIN_LENGTH) {
            throw new ClubQuestionException(ClubQuestionErrorCode.CLUB_CREATE_JOIN_QUESTION_TOO_SHORT);
        }
        if (question.length() > MAX_LENGTH) {
            throw new ClubQuestionException(ClubQuestionErrorCode.CLUB_CREATE_JOIN_QUESTION_TOO_LONG);
        }
    }

}
