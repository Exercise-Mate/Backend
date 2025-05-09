package com.f3.exercise_mate.club.application.service;

import com.f3.exercise_mate.appointment.domain.JoinStatus;
import com.f3.exercise_mate.club.application.dto.JoinClubRequestDto;
import com.f3.exercise_mate.club.domain.Club;
import com.f3.exercise_mate.club.domain.ClubGrade;
import com.f3.exercise_mate.club.domain.ClubJoinAnswer;
import com.f3.exercise_mate.club.domain.ClubJoinAnswerList;
import com.f3.exercise_mate.club.domain.ClubMember;
import com.f3.exercise_mate.club.repository.FakeClubJoinAnswerRepository;
import com.f3.exercise_mate.club.repository.FakeClubMemberRepository;
import com.f3.exercise_mate.common.entity.SportAbility;
import com.f3.exercise_mate.common.exception.ExerciseMateException;
import com.f3.exercise_mate.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClubJoinServiceTest {

    private FakeClubJoinAnswerRepository clubJoinAnswerRepository;
    private FakeClubMemberRepository clubMemberRepository;
    private ClubJoinService clubJoinService;
    private Club club;
    private User user;
    private JoinClubRequestDto joinClubRequestDto;

    @BeforeEach
    public void setUp() {
        clubJoinAnswerRepository = new FakeClubJoinAnswerRepository();
        clubMemberRepository = new FakeClubMemberRepository();
        clubJoinService = new ClubJoinService(clubJoinAnswerRepository, clubMemberRepository);

        club = Club.builder()
                .name("DM 크루")
                .sportType("클라이밍")
                .mainArea("서울 은평구")
                .sportAbilityMin(SportAbility.BEGINNER)
                .sportAbilityMax(SportAbility.EXPERT)
                .ageLimitMin(20)
                .ageLimitMax(40)
                .description("클라이밍 같이 즐겨요!")
                .build();

        user = new User(1L, 30, "john_doe");
    }

    @DisplayName("유효한 답변은 저장된다.")
    @Test
    void saveJoinAnswerValidTest() {
        // given
        ClubJoinAnswer validAnswer = new ClubJoinAnswer(1L, 1L, 1L, "열심히 하겠습니다.");
        ClubJoinAnswerList answerList = new ClubJoinAnswerList(java.util.Arrays.asList(validAnswer));

        // when
        clubJoinService.saveJoinAnswer(answerList);

        // then
        assertEquals(1, clubJoinAnswerRepository.size());
        ClubJoinAnswer savedAnswer = clubJoinAnswerRepository.findById(1L);
        assertEquals("열심히 하겠습니다.", savedAnswer.getAnswer());
    }

    @DisplayName("유효하지 않은 답변은 예외가 발생한다.")
    @Test
    void saveJoinAnswerInvalidTest() {
        // given
        ClubJoinAnswer invalidAnswer = new ClubJoinAnswer(1L, 1L, 1L, "abc");
        ClubJoinAnswerList answerList = new ClubJoinAnswerList(java.util.Arrays.asList(invalidAnswer));

        // when & then
        assertThrows(ExerciseMateException.class, () -> clubJoinService.saveJoinAnswer(answerList));
    }

//    @DisplayName("클럽 가입 신청 시, 이미 가입된 회원이면 예외가 발생한다.")
//    @Test
//    void joinClubAlreadyJoined() {
//        // given
//        ClubMember existingMember = new ClubMember(1L, user, club, JoinStatus.PENDING, SportAbility.BEGINNER, ClubGrade.CLUB_MEMBER);
//        clubMemberRepository.save(existingMember);
//
//        // when
//        JoinClubRequestDto joinClubRequestDto = new JoinClubRequestDto(club, user, SportAbility.BEGINNER, new ClubJoinAnswerList(java.util.Arrays.asList(new ClubJoinAnswer(1L, 1L, 1L, "열심히 하겠습니다."))));
//
//        // then
//        assertThrows(ExerciseMateException.class, () -> clubJoinService.joinClub(joinClubRequestDto));
//    }
//
//    @DisplayName("클럽 가입 신청 시, 유효한 요청에 대해 정상적으로 처리된다.")
//    @Test
//    void joinClubSuccess() {
//        // given
//        ClubJoinAnswer validAnswer = new ClubJoinAnswer(1L, 1L, 1L, "열심히 하겠습니다.");
//        ClubJoinAnswerList clubJoinAnswerList = new ClubJoinAnswerList(java.util.Arrays.asList(validAnswer));
//        JoinClubRequestDto joinClubRequestDto = new JoinClubRequestDto(club, user, SportAbility.BEGINNER, clubJoinAnswerList);
//
//        // when
//        ClubMember createdMember = clubJoinService.joinClub(joinClubRequestDto);
//
//        // then
//        assertEquals(JoinStatus.PENDING, createdMember.getStatus());
//        assertEquals(1, clubMemberRepository.size());
//    }
//
//    @DisplayName("클럽 가입 승인 시 상태 변경 및 등급 변경")
//    @Test
//    void approveJoinClub() {
//        // given
//        ClubMember clubMember = new ClubMember(1L, user, club, JoinStatus.PENDING, SportAbility.BEGINNER, ClubGrade.CLUB_MEMBER);
//        clubMemberRepository.save(clubMember);
//
//        // when
//        clubJoinService.approveJoinClub(1L, ClubGrade.CLUB_LEADER);
//
//        // then
//        ClubMember updatedMember = clubMemberRepository.findByClubMemberId(1L);
//        assertEquals(JoinStatus.ACCEPTED, updatedMember.getStatus());
//        assertEquals(ClubGrade.CLUB_LEADER, updatedMember.getGrade());
//    }
//
//    @DisplayName("클럽 가입 거절 시 상태 변경")
//    @Test
//    void rejectJoinClub() {
//        // given
//        ClubMember clubMember = new ClubMember(1L, user, club, JoinStatus.PENDING, SportAbility.BEGINNER, ClubGrade.CLUB_MEMBER);
//        clubMemberRepository.save(clubMember);
//
//        // when
//        clubJoinService.rejectJoinClub(1L); // 가입 거절
//
//        // then
//        ClubMember updatedMember = clubMemberRepository.findByClubMemberId(1L);
//        assertEquals(JoinStatus.REJECTED, updatedMember.getStatus());
//    }
}
