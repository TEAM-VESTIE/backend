package com.vestie.vestie.survey.presentation;

import com.vestie.vestie.survey.application.SurveyService;
import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    ResponseEntity<Long> register(@RequestBody SurveyRegisterRequest request) {
        Long id = 1L;
        surveyService.register(request.toCommand(id));
        return ResponseEntity.status(CREATED).build();
    }
}
