package com.f3.exercise_mate.club.ui.controller;

import com.f3.exercise_mate.club.application.dto.CreateClubRequestDto;
import com.f3.exercise_mate.club.application.service.ClubService;
import com.f3.exercise_mate.club.domain.Club;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PostMapping(value = "/club/create")
    public ResponseEntity<Club> createClub(@RequestBody CreateClubRequestDto createClubRequestDto) {
        Club createdClub = clubService.createClub(createClubRequestDto);
        return ResponseEntity.ok(createdClub);
    }
}
