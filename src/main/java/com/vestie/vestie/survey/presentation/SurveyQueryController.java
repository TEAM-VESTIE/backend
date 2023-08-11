package com.vestie.vestie.survey.presentation;

import com.vestie.vestie.common.annotation.Auth;
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
public class SurveyQueryController {

    private final SurveyQueryService surveyQueryService;

    @GetMapping("/query")
    ResponseEntity<List<SurveyInquiryResponse>> getAllSurvey() {
        List<SurveyInquiryResponse> allSurvey = surveyQueryService.getAllSurvey();
        return ResponseEntity.ok(allSurvey);
    }

    @GetMapping("/{surveyId}")
    ResponseEntity<SurveyResponse> getSurvey(@Auth Long memberId, @PathVariable Long surveyId) {
        SurveyResponse survey = surveyQueryService.getSurvey(surveyId);
        System.out.println(survey.survey().get(0).title());
        return ResponseEntity.ok(survey);
    }
}
