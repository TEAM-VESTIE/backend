package com.vestie.vestie.survey.presentation;

import com.vestie.vestie.survey.application.SurveyQueryService;
import com.vestie.vestie.survey.presentation.dto.SurveyInquiryResponse;
import com.vestie.vestie.survey.presentation.dto.SurveyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys/query")
public class SurveyQueryController {

    private final SurveyQueryService surveyQueryService;

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
