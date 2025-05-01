package com.f3.exercise_mate.club.repository;

import com.f3.exercise_mate.club.application.interfaces.ClubJoinQuestionRepository;
import com.f3.exercise_mate.club.domain.ClubJoinQuestion;

import java.util.HashMap;
import java.util.Map;

public class FakeClubJoinQuestionRepository implements ClubJoinQuestionRepository {

    private final Map<Long, ClubJoinQuestion> store = new HashMap<>();
    private long idSequence = 1L;

    @Override
    public ClubJoinQuestion save(ClubJoinQuestion clubJoinQuestion) {
        store.put(idSequence++, clubJoinQuestion);
        return clubJoinQuestion;
    }

    @Override
    public ClubJoinQuestion findById(Long id) {
        return store.get(id);
    }
}
