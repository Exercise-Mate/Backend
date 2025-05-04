package com.f3.exercise_mate.chat.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateRoomReqDTO {

    private String chatRoomName;
    private Long memberId;
}
