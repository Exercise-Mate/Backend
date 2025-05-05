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
    public ChatParticipation findOne(Long memberId, Long chatRoomId) {
        ChatParticipationKey targetKey = new ChatParticipationKey(memberId, chatRoomId);
        ChatParticipation findedChatParticipation = chatParticipationMap.get(targetKey);
        return findedChatParticipation;
    }

    // 굳이 필요할까? 서비스단에서 findOne을 통해 참여시점을 알아낼 수 있는데?
    // -> ChatParticipaion 정보는 필요하지 않고, participatedAt만 사용하는 경우가 많다면 유지하자.
    // -> 유지한다면, findOne은 필요 없으므로 getParticipatedAt 내에 통합 vs 확장성을 위해 유지
    @Override
    public String getParticipatedAtByMemberAndChatRoom(Long memberId, Long chatRoomId) {
        ChatParticipation findedChatParticipation = findOne(memberId, chatRoomId);
        return findedChatParticipation.getParticipatedAt();
    }
}
