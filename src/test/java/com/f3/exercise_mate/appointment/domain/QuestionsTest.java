package com.f3.exercise_mate.appointment.domain;

import com.f3.exercise_mate.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsTest {

    private final User creator = new User(1L, 20, "john");
    private final Location location = new Location("서울시 강남구", "강남 풋살장", "");
    private final DateInfo dateInfo = new DateInfo(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1));
    private final Appointment appointment = Appointment.create(
            1L,
            "풋살 한판 뛰실분?",
            creator,
            "풋살 재밌게 찹시다.",
            Sport.FUTSAL,
            location,
            dateInfo,
            10);

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
        Question question = new Question(1L, appointment, "this is a content");

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
        String text = "this is a content";
        Question question = new Question(1L, appointment, text);

        // when
        questions.add(question);

        // then
        assertEquals(1, questions.size());
        assertEquals(1L, questions.getQuestions().get(0).getId());
        assertEquals(text, questions.getQuestions().get(0).getContent());
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
        String text = "this is a content";

        Question question1 = new Question(1L, appointment, text + 1);
        Question question2 = new Question(2L, appointment, text + 2);
        Question question3 = new Question(3L, appointment, text + 3);
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