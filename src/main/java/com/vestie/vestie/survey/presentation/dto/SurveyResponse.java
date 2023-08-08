package com.vestie.vestie.survey.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vestie.vestie.survey.domain.Option;

import static com.vestie.vestie.survey.domain.MultipleChoiceQuestion.ChoiceConstraints;


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
        public static abstract class QuestionResponse {
                private Long id;
                private String format;
                private String title;
                public QuestionResponse(Long id, String format, String title) {
                        this.id = id;
                        this.format = format;
                        this.title = title;
                }
        }

        public static class SubjectiveQuestionResponse extends QuestionResponse{
                public SubjectiveQuestionResponse(Long id, String format, String title){
                        super(id, format, title);
                }
        }

        public static class MultipleChoiceQuestionResponse extends QuestionResponse{
                private ChoiceConstraints choiceConstraints;
                private List<OptionResponse> options;

                public MultipleChoiceQuestionResponse(Long id, String format, String title, ChoiceConstraints choiceConstraints, List<Option> options) {
                        super(id, format, title);
                        this.choiceConstraints = choiceConstraints;
                        this.options = options.stream()
                                .map((o)->new OptionResponse(o.id(), o.name()))
                                .toList();
                }
        }

        public static class OptionResponse {
                private Long id;
                private String option;
                public OptionResponse (Long id, String option) {
                        this.id = id;
                        this.option = option;
                }
        }
}
