package com.vestie.vestie.survey.application;

import com.vestie.vestie.survey.domain.*;
import com.vestie.vestie.survey.presentation.dto.*;

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

    public SurveyResponse getSurvey(Long surveyId) {
        Survey survey = surveyRepository.getById(surveyId);
        List<QuestionResponse> questionResponses =
                survey.questions().stream()
                        .map(this::questionResponse)
                        .toList();

        return new SurveyResponse(survey.id(), survey.title(), survey.description(), survey.endDate(), questionResponses);
    }

    private QuestionResponse questionResponse(Question question) {
        if(question instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) question;
            return new MultipleChoiceQuestionResponse(
                    question.id(),
                    QuestionType.MULTIPLE_CHOICE.name(),
                    question.title(),
                    multipleChoiceQuestion.choiceConstraints(),
                    multipleChoiceQuestion.options().stream()
                            .map(this::questionOptionResponse)
                            .toList()
            );
        }
        return new SubjectiveQuestionResponse(question.id(), QuestionType.SUBJECTIVE.name(), question.title());
    }

    private QuestionOptionResponse questionOptionResponse(Option option) {
        return new QuestionOptionResponse(option.id(), option.name());
    }
}
