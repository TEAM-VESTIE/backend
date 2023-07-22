package com.vestie.vestie.survey.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import com.vestie.vestie.common.annotation.Auth;
import com.vestie.vestie.survey.application.SurveyService;
import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;
//    private final SurveyQueryService surveyQueryService;

    @PostMapping
    ResponseEntity<Long> register(
            @Auth Long memberId,
            @RequestBody SurveyRegisterRequest request) {
        surveyService.register(request.toCommand(memberId));
        return ResponseEntity.status(CREATED).build();
    }

//    @GetMapping
//    ResponseEntity<List<SurveyInquiryResponse>> getAllSurvey() {
//        List<SurveyInquiryResponse> allSurvey = surveyQueryService.getAllSurvey();
//        return ResponseEntity.ok(allSurvey);
//    }
//
//    @GetMapping("/{surveyId}")
//    ResponseEntity<SurveyResponse> getSurvey(@PathVariable Long surveyId) {
//        SurveyResponse survey = surveyQueryService.getSurvey(surveyId);
//        return ResponseEntity.ok(survey);
//    }
}
