package com.f3.exercise_mate.chat.ui.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessageResponseDTO {
    private Long memberId;
    private String message;
    private String sendAt;
}
