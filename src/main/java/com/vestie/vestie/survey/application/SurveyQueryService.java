package com.vestie.vestie.survey.application;

import com.vestie.vestie.survey.domain.*;
import com.vestie.vestie.survey.presentation.dto.SurveyInquiryResponse;
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
        System.out.println(surveyList.size());
        List<SurveyInquiryResponse> commandList = new ArrayList<>();
        for (Survey survey : surveyList) {
            commandList.add(
                    new SurveyInquiryResponse(
                            survey.id(),
                            survey.title(),
                            survey.endDate(),
                            (long) survey.questions().size()
                    )
            );
        }
        return commandList;
    }
}
