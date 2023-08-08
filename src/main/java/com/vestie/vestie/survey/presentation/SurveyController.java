package com.vestie.vestie.survey.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import com.vestie.vestie.common.annotation.Auth;
import com.vestie.vestie.survey.application.SurveyService;
import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest;
import com.vestie.vestie.survey.presentation.dto.SurveyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    ResponseEntity<Long> register(
            @Auth Long memberId,
            @RequestBody SurveyRegisterRequest request) {
        surveyService.register(request.toCommand(memberId));
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/{surveyId}")
    ResponseEntity<SurveyResponse> getSurvey(@PathVariable Long surveyId) {
        SurveyResponse survey = surveyService.getSurvey(surveyId);
        return ResponseEntity.ok(survey);
    }
}
