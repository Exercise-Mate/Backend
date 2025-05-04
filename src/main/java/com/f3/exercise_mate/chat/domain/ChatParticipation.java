package com.f3.exercise_mate.chat.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatParticipation {
    private Long id;
    private Long memberId;
    private Long chatRoomId;
    private String participatedAt;

    public static ChatParticipation create(Long id, Long memberId, Long chatRoomId, String participatedAt){
        ChatParticipation participation = new ChatParticipation();

        participation.id = id;
        participation.memberId = memberId;
        participation.chatRoomId = chatRoomId;
        participation.participatedAt = participatedAt;

        participation.validate();
        return participation;
    }

    // 유효성 검증
    private void validate() {

    }
}
