package com.vestie.vestie.survey.application;

import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.survey.application.dto.SurveyCommand;
import com.vestie.vestie.survey.application.dto.SurveyInquiryCommand;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import com.vestie.vestie.survey.domain.Survey;
import com.vestie.vestie.survey.domain.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<SurveyInquiryCommand> getAllSurvey(Long memberId) {
        List<Survey> surveyList = surveyRepository.findAllByMemberId(memberId);
        List<SurveyInquiryCommand> commandList = new ArrayList<>();
        for (Survey s : surveyList) {
            commandList.add(new SurveyInquiryCommand(s.id(), s.title(), s.endDate()));
        }
        return commandList;
    }

    public SurveyCommand getSurvey(Long memberId, Long surveyId) {
        Survey survey = surveyRepository.getById(surveyId);
        return new SurveyCommand(survey.id(), survey.title(), survey.formLink(), survey.endDate());
    }
}