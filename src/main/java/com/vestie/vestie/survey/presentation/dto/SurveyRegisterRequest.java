package com.vestie.vestie.survey.presentation.dto;

import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record SurveyRegisterRequest (
        @NotBlank String formLink,
        @NotBlank LocalDateTime endDate
) {

    public SurveyRegisterCommand toCommand(Long memberId) {
        return new SurveyRegisterCommand(memberId, formLink, endDate);
    }
}
