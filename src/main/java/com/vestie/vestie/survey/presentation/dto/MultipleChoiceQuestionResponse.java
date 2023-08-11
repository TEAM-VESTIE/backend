package com.vestie.vestie.survey.presentation.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.vestie.vestie.survey.domain.MultipleChoiceQuestion;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MultipleChoiceQuestionResponse extends QuestionResponse{
    private MultipleChoiceQuestion.ChoiceConstraints choiceConstraints;
    private List<QuestionOptionResponse> options;

    public MultipleChoiceQuestionResponse(Long id, String format, String title, MultipleChoiceQuestion.ChoiceConstraints choiceConstraints, List<QuestionOptionResponse> options) {
        super(id, format, title);
        this.choiceConstraints = choiceConstraints;
        this.options = options;
    }
}
