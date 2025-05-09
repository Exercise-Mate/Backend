package com.f3.exercise_mate.club.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ClubJoinAnswerList {

    private List<ClubJoinAnswer> clubJoinAnswersList;

    public ClubJoinAnswerList(List<ClubJoinAnswer> clubJoinAnswersList) {
        this.clubJoinAnswersList = validate(clubJoinAnswersList);
    }

    private List<ClubJoinAnswer> validate(List<ClubJoinAnswer> clubJoinAnswersList) {
        if (clubJoinAnswersList == null || clubJoinAnswersList.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(clubJoinAnswersList);
    }
}
