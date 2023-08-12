package com.vestie.vestie.survey.presentation.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SubjectiveQuestionResponse extends QuestionResponse {

    public SubjectiveQuestionResponse(Long id, String format, String title){
        super(id, format, title);
    }
}