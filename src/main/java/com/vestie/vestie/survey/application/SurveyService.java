package com.vestie.vestie.survey.application;

import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import com.vestie.vestie.survey.domain.Survey;
import com.vestie.vestie.survey.domain.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyService {

    private final MemberRepository memberRepository;
    private final SurveyRepository surveyRepository;

    public Long register(SurveyRegisterCommand command) {
        Member member = memberRepository.getById(command.memberId());
        Survey survey = command.toDomain();
        survey.setMember(member);
        Survey savedSurvey = surveyRepository.save(survey);
        return savedSurvey.id();
    }

}
