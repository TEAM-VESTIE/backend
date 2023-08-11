package com.vestie.vestie.survey.presentation.dto;

import com.vestie.vestie.survey.domain.QuestionType;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class QuestionResponse {
    private Long id;
    private String format;
    private String title;
    public QuestionResponse(Long id, String format, String title) {
        this.id = id;
        this.format = format;
        this.title = title;
    }
}
