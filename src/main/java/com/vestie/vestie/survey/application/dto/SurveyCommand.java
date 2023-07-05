package com.vestie.vestie.survey.application.dto;

import java.time.LocalDateTime;

public record SurveyCommand (
        Long id,
        String title,
        String formLink,
        LocalDateTime endDate
) {
}
