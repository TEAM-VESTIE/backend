package com.vestie.vestie.survey.application.dto;

import java.time.LocalDateTime;

public record SurveyInquiryCommand (
        Long id,
        String title,
        LocalDateTime endDate
) {


}
