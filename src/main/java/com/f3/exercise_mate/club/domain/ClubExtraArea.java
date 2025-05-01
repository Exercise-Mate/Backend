package com.f3.exercise_mate.club.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubExtraArea {

    private Long clubExtraAreaId;
    private Long clubId;
    private String area;
}
