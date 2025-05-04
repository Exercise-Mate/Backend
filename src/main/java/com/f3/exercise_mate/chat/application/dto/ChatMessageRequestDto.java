package com.f3.exercise_mate.chat.application.dto;

public record ChatMessageRequestDto(Long memberId, Long chatRoomId, String message, String sendAt) {
}