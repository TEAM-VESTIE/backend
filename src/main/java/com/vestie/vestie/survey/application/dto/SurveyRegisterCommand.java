package com.vestie.vestie.survey.application.dto;

import com.vestie.vestie.survey.domain.Survey;

import java.time.LocalDateTime;

public record SurveyRegisterCommand(
        Long memberId,
        String formLink,
        LocalDateTime endDate
) {
    public Survey toDomain() {
        return Survey.builder()
                .formLink(formLink)
                .endDate(endDate)
                .build();
    }
}
