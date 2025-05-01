package com.f3.exercise_mate.club.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ClubJoinQuestionList {
    private List<ClubJoinQuestion> clubJoinQuestionsList;

    public ClubJoinQuestionList(List<ClubJoinQuestion> clubJoinQuestionsList) {
        this.clubJoinQuestionsList = validate(clubJoinQuestionsList);
    }

    private List<ClubJoinQuestion> validate(List<ClubJoinQuestion> clubJoinQuestionsList) {
        if(clubJoinQuestionsList == null || clubJoinQuestionsList.isEmpty()) {
            return new ArrayList<>();
        }
        List<ClubJoinQuestion> validatedList = new ArrayList<>();
        for(ClubJoinQuestion clubJoinQuestion : clubJoinQuestionsList) {
            validatedList.add(new ClubJoinQuestion(
                    clubJoinQuestion.getQuestionId(),
                    clubJoinQuestion.getClubId(),
                    clubJoinQuestion.getQuestion()
            ));
        }
        return validatedList;
    }
}
