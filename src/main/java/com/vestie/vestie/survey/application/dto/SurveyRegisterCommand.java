package com.vestie.vestie.survey.application.dto;

import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.survey.domain.Survey;

import java.time.LocalDateTime;

public record SurveyRegisterCommand(
        Long memberId,
        String title,
        String formLink,
        LocalDateTime endDate
) {
    
    public Survey toDomain(Member member) {
        return Survey.builder()
                .member(member)
                .title(title)
                .formLink(formLink)
                .endDate(endDate)
                .build();
    }
}
