package com.vestie.vestie.survey.presentation;

import com.vestie.vestie.survey.application.SurveyQueryService;
import com.vestie.vestie.survey.application.SurveyService;
import com.vestie.vestie.survey.presentation.dto.SurveyInquiryResponse;
import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest;
import com.vestie.vestie.survey.presentation.dto.SurveyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;
    private final SurveyQueryService surveyQueryService;

    @PostMapping
    ResponseEntity<Long> register(@RequestBody SurveyRegisterRequest request) {
        Long id = 1L;
        surveyService.register(request.toCommand(id));
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    ResponseEntity<List<SurveyInquiryResponse>> getAllSurvey() {
        List<SurveyInquiryResponse> allSurvey = surveyQueryService.getAllSurvey();
        return ResponseEntity.ok(allSurvey);
    }

    @GetMapping("/{surveyId}")
    ResponseEntity<SurveyResponse> getSurvey(@PathVariable Long surveyId) {
        SurveyResponse survey = surveyQueryService.getSurvey(surveyId);
        return ResponseEntity.ok(survey);
    }
}
