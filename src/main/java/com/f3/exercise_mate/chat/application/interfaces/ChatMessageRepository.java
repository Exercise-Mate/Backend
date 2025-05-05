package com.f3.exercise_mate.chat.application.interfaces;

import com.f3.exercise_mate.chat.domain.ChatMessage;

import java.util.List;

public interface ChatMessageRepository {

    Long save(Long memberId, Long chatRoomId, String message, String sendAt);

    List<ChatMessage> findRecent30ByMemberAndChatRoomAfterParticipation(Long memberId, Long chatRoomId, String participatedAt);
}
