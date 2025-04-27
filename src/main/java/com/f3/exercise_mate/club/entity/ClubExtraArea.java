package com.f3.exercise_mate.club.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "club_extra_area")
public class ClubExtraArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubExtraAreaId;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    private String area;
}

