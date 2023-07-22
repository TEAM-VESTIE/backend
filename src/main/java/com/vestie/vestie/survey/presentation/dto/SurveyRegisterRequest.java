package com.vestie.vestie.survey.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand.MultipleChoiceQuestionCommand;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand.QuestionRegisterCommand;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand.SubjectiveQuestionCommand;
import com.vestie.vestie.survey.domain.MultipleChoiceQuestion.ChoiceConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public record SurveyRegisterRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") LocalDateTime endDate,
        @NotEmpty List<QuestionRegisterRequest> questionRequests
) {

    @Getter
    @JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "format")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = MultipleChoiceQuestionRequest.class, name = "MULTIPLE_CHOICE"),
            @JsonSubTypes.Type(value = SubjectiveQuestionRequest.class, name = "SUBJECTIVE")
    })
    public static abstract class QuestionRegisterRequest {

        protected final String format;
        protected final String title;

        public QuestionRegisterRequest(String format, String title) {
            this.format = format;
            this.title = title;
        }

        public abstract QuestionRegisterCommand toCommand();
    }

    @Getter
    public static class MultipleChoiceQuestionRequest extends QuestionRegisterRequest {

        private final ChoiceConstraints choiceConstraints;
        private final List<String> options = new ArrayList<>();

        public MultipleChoiceQuestionRequest(
                String format,
                String title,
                ChoiceConstraints choiceConstraints,
                List<String> options
        ) {
            super(format, title);
            this.choiceConstraints = choiceConstraints;
            this.options.addAll(options);
        }

        @Override
        public QuestionRegisterCommand toCommand() {
            return new MultipleChoiceQuestionCommand(
                    title,
                    choiceConstraints,
                    options
            );
        }
    }

    public static class SubjectiveQuestionRequest extends QuestionRegisterRequest {

        public SubjectiveQuestionRequest(String format, String title) {
            super(format, title);
        }

        @Override
        public QuestionRegisterCommand toCommand() {
            return new SubjectiveQuestionCommand(title);
        }
    }

    public SurveyRegisterCommand toCommand(Long memberId) {
        List<QuestionRegisterCommand> questions = questionRequests.stream()
                .map(QuestionRegisterRequest::toCommand)
                .toList();
        return SurveyRegisterCommand.builder()
                .memberId(memberId)
                .title(title)
                .description(description)
                .endDate(endDate)
                .questionCommands(questions)
                .build();
    }
}
