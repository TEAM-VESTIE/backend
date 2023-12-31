package com.vestie.vestie.survey.application;

import com.vestie.vestie.survey.domain.Survey;
import com.vestie.vestie.survey.domain.SurveyRepository;
import com.vestie.vestie.survey.presentation.dto.SurveyInquiryResponse;
import com.vestie.vestie.survey.presentation.dto.SurveyResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyQueryService {

    private final SurveyRepository surveyRepository;

    public List<SurveyInquiryResponse> getAllSurvey() {
        List<Survey> surveyList = surveyRepository.findAll();
        List<SurveyInquiryResponse> commandList = new ArrayList<>();
        for (Survey survey : surveyList) {
            commandList.add(new SurveyInquiryResponse(survey.id(), survey.title(), survey.endDate()));
        }
        return commandList;
    }

    public SurveyResponse getSurvey(Long surveyId) {
        Survey survey = surveyRepository.getById(surveyId);
        return new SurveyResponse(survey.id(), survey.title(), "removed", survey.endDate());
    }
}
