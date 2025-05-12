package com.f3.exercise_mate.appointment.application.service;

import com.f3.exercise_mate.appointment.application.interfaces.QuestionRepository;
import com.f3.exercise_mate.appointment.domain.Appointment;
import com.f3.exercise_mate.appointment.domain.Question;
import com.f3.exercise_mate.appointment.repository.entity.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    // TODO : 질문에 대한 서비스 레이어가 필요할지 아직 정확하게 모른다. 사용치 않을시 삭제
    private final QuestionRepository questionRepository;

}
