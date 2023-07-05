package com.vestie.vestie.survey.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record SurveyRegisterRequest (
        @NotBlank String formLink,
        @NotBlank @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") LocalDateTime endDate
) {

    public SurveyRegisterCommand toCommand(Long memberId) {
        return new SurveyRegisterCommand(memberId, formLink, endDate);
    }
}
