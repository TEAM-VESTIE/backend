package com.vestie.vestie.survey.application;

import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import com.vestie.vestie.survey.domain.*;
import com.vestie.vestie.survey.presentation.dto.*;
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