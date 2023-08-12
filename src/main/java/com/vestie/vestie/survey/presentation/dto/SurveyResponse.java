package com.vestie.vestie.survey.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;


public record SurveyResponse(
        Long id,
        String title,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime endDate,
        List<QuestionResponse> survey
) {
}
