package com.vestie.vestie.survey.application;

import com.vestie.vestie.survey.domain.Survey;
import com.vestie.vestie.survey.domain.SurveyRepository;
import com.vestie.vestie.survey.presentation.dto.SurveyInquiryResponse;
import com.vestie.vestie.survey.presentation.dto.SurveyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyQueryService {

    private final SurveyRepository surveyRepository;

    public List<SurveyInquiryResponse> getAllSurvey() {
        List<Survey> surveyList = surveyRepository.findAll();
        List<SurveyInquiryResponse> commandList = new ArrayList<>();
        for (Survey s : surveyList) {
            commandList.add(new SurveyInquiryResponse(s.id(), s.title(), s.endDate()));
        }
        return commandList;
    }

    public SurveyResponse getSurvey(Long surveyId) {
        Survey survey = surveyRepository.getById(surveyId);
        return new SurveyResponse(survey.id(), survey.title(), survey.formLink(), survey.endDate());
    }
}
