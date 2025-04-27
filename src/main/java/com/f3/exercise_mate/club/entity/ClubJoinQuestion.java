package com.f3.exercise_mate.club.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "club_join_question")
public class ClubJoinQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    private String question;
}

