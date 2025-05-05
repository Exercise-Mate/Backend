package com.f3.exercise_mate.chat.repository.fake;

import com.f3.exercise_mate.chat.application.interfaces.ChatMessageRepository;
import com.f3.exercise_mate.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FakeChatMessageRepository implements ChatMessageRepository {

    private Long chatMessageId = 1L;
    private final Map<Long, ChatMessage> chatMessageMap = new ConcurrentHashMap<>();

    @Override
    public Long save(Long memberId, Long chatRoomId, String message, String sendAt) {
        ChatMessage chatMessage = ChatMessage.create(chatMessageId++, memberId, chatRoomId, message, sendAt);

        if (chatMessage.getId() != chatMessageId - 1) {
            // 예상되는 id값과 일치하지 않는 경우 에러 발생

        }

        chatMessageMap.put(chatMessageId, chatMessage);
        return chatMessage.getId();
    }

    @Override
    public List<ChatMessage> findRecent30ByMemberAndChatRoomAfterParticipation(Long memberId, Long chatRoomId, String participatedAt) {
        List<ChatMessage> result = chatMessageMap.values().stream()
                .filter(chatMessage -> chatMessage.getChatRoomId().equals(chatRoomId)
                        && LocalDateTime.parse(chatMessage.getSendAt()).isAfter(LocalDateTime.parse(participatedAt)))
                .sorted(Comparator.comparing(ChatMessage::getSendAt))       // 오래된 순 정렬
                .toList();

        return result.stream()
                .skip(Math.max(0, result.size() - 30))      // 마지막 30개만 추출
                .collect(Collectors.toList());
    }
}
