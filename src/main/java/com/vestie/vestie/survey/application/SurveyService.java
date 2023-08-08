package com.vestie.vestie.survey.application;

import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import com.vestie.vestie.survey.domain.*;
import com.vestie.vestie.survey.presentation.dto.SurveyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class SurveyService {

    private final MemberRepository memberRepository;
    private final SurveyRepository surveyRepository;

    public Long register(SurveyRegisterCommand command) {
        Member member = memberRepository.getById(command.memberId());
        Survey survey = command.toDomain(member);
        Survey savedSurvey = surveyRepository.save(survey);
        return savedSurvey.id();
    }

    public SurveyResponse getSurvey(Long surveyId) {
        Survey survey = surveyRepository.getById(surveyId);
        List<SurveyResponse.QuestionResponse> questionResponses =
                survey.questions().stream()
                        .map(this::questionResponse)
                        .toList();

        return new SurveyResponse(survey.id(), survey.title(), survey.description(), survey.endDate(), questionResponses);
    }

    private SurveyResponse.QuestionResponse questionResponse(Question question) {
        if(question instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) question;
            return new SurveyResponse.MultipleChoiceQuestionResponse(
                    question.id(),
                    SubjectiveQuestion.class.getSimpleName(),
                    question.title(),
                    multipleChoiceQuestion.choiceConstraints(),
                    multipleChoiceQuestion.options());
        }
        return new SurveyResponse.SubjectiveQuestionResponse(question.id(), SubjectiveQuestion.class.getSimpleName(), question.title());
    }
}