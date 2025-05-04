package com.f3.exercise_mate.chat.repository.fake;

import com.f3.exercise_mate.chat.application.interfaces.ChatParticipationRepository;
import com.f3.exercise_mate.chat.domain.ChatParticipation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class FakeChatParticipationRepository implements ChatParticipationRepository {

    // 커스텀 복합키
    private record ChatParticipationKey(Long memberId, Long chatRoomId) {
    }

    private Long chatParticipationId = 1L;
    private final Map<ChatParticipationKey, ChatParticipation> chatParticipationMap = new ConcurrentHashMap<>();

    @Override
    public Long save(Long memberId, Long chatRoomId, String participatedAt) {
        ChatParticipation participation = ChatParticipation.create(chatParticipationId++, memberId, chatRoomId, participatedAt);

        if(participation.getId() != chatParticipationId - 1) {
            // 예상되는 id값과 일치하지 않는 경우 에러 발생

        }

        ChatParticipationKey key = new ChatParticipationKey(participation.getMemberId(), participation.getChatRoomId());
        chatParticipationMap.put(key, participation);
        return participation.getId();
    }

    @Override
    public String findParticipatedAtByMemberAndChatRoom(Long memberId, Long chatRoomId) {
        ChatParticipationKey targetKey = new ChatParticipationKey(memberId, chatRoomId);
        ChatParticipation findedChatParticipation = chatParticipationMap.get(targetKey);
        return findedChatParticipation.getParticipatedAt();
    }
}
