package com.f3.exercise_mate.club.repository;

import com.f3.exercise_mate.club.domain.ClubJoinAnswer;
import com.f3.exercise_mate.club.application.interfaces.ClubJoinAnswerRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeClubJoinAnswerRepository implements ClubJoinAnswerRepository {

    private final Map<Long, ClubJoinAnswer> store = new HashMap<>();
    private long idSequence = 1L;

    @Override
    public ClubJoinAnswer save(ClubJoinAnswer clubJoinAnswer) {
        ClubJoinAnswer newAnswer = ClubJoinAnswer.builder()
                .answerId(idSequence++)
                .questionId(clubJoinAnswer.getQuestionId())
                .clubMemberId(clubJoinAnswer.getClubMemberId())
                .answer(clubJoinAnswer.getAnswer())
                .build();

        store.put(newAnswer.getAnswerId(), newAnswer);
        return newAnswer;
    }

    public int size() {
        return store.size();
    }

    public ClubJoinAnswer findById(Long answerId) {
        return store.get(answerId);
    }
}
