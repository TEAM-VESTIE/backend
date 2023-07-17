package com.vestie.vestie.survey.presentation;

import com.vestie.vestie.auth.presentation.AuthenticationContext;
import com.vestie.vestie.common.annotation.Auth;
import com.vestie.vestie.survey.application.SurveyService;
import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;
    private final AuthenticationContext context;

    @PostMapping
    ResponseEntity<Void> register(@Auth Long memberId, @RequestBody SurveyRegisterRequest request) {
        surveyService.register(request.toCommand(memberId));
        return ResponseEntity.status(CREATED).build();
    }
}
