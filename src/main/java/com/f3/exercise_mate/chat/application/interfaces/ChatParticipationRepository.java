package com.f3.exercise_mate.chat.application.interfaces;

import com.f3.exercise_mate.chat.domain.ChatParticipation;

public interface ChatParticipationRepository {
    Long save(Long memberId, Long chatRoomId, String participatedAt);

    ChatParticipation findOne(Long memberId, Long chatRoomId);

    String getParticipatedAtByMemberAndChatRoom(Long memberId, Long chatRoomId);
}
