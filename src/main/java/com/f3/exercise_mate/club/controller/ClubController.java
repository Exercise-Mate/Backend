package com.f3.exercise_mate.club.controller;

import com.f3.exercise_mate.club.entity.Club;
import com.f3.exercise_mate.club.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Club createClub(@RequestBody Club club) {
        return clubService.createClub(club);
    }
}

