package com.f3.exercise_mate.chat.application.interfaces;

public interface ChatParticipationRepository {
    Long save(Long memberId, Long chatRoomId, String participatedAt);

    String findParticipatedAtByMemberAndChatRoom(Long memberId, Long chatRoomId);
}
