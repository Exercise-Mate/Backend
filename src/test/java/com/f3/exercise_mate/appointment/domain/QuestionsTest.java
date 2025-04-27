package com.f3.exercise_mate.appointment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsTest {

    @Test
    @DisplayName("null이 들어오면 빈 ArrayList가 생성 된다.")
    void givenNull_createEmptyQuestions() {
        // given
        Questions questions = new Questions(null);

        // when, then
        assertEquals(0, questions.size());
        assertTrue(questions.getQuestions().isEmpty());
    }

    @Test
    @DisplayName("null이 있는 List로 객체를 생성할 때 에러 발생")
    void givenNullQuestion_createQuestions_throwError() {
        // given
        Question nullQuestion = null;
        Question question = new Question(1L, 1L, "this is a question");

        List<Question> list = new ArrayList<>();
        list.add(nullQuestion);
        list.add(question);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Questions(list));
    }

    @Test
    @DisplayName("질문 추가 성공")
    void questions_add_success() {
        // given
        Questions questions = new Questions();
        String text = "this is a question";
        Question question = new Question(1L, 1L, text);

        // when
        questions.add(question);

        // then
        assertEquals(1, questions.size());
        assertEquals(1L, questions.getQuestions().get(0).getId());
        assertEquals(text, questions.getQuestions().get(0).getQuestion());
    }

    @Test
    @DisplayName("질문 추가 실패")
    void questions_add_fail() {
        // given
        Questions questions = new Questions();

        // when, then
        assertThrows(IllegalArgumentException.class, () -> questions.add(null));
    }

    @Test
    @DisplayName("질문 삭제 테스트")
    void questions_remove_success() {
        // given
        Questions questions = new Questions();
        String text = "this is a question";

        Question question1 = new Question(1L, 1L, text + 1);
        Question question2 = new Question(2L, 1L, text + 2);
        Question question3 = new Question(3L, 1L, text + 3);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        // when
        questions.remove(question2);

        // then
        assertEquals(2, questions.size());
        assertFalse(questions.getQuestions().contains(question2));
    }


}