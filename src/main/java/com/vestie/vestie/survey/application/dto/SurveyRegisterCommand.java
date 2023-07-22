package com.vestie.vestie.survey.application.dto;

import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.survey.domain.MultipleChoiceQuestion;
import com.vestie.vestie.survey.domain.MultipleChoiceQuestion.ChoiceConstraints;
import com.vestie.vestie.survey.domain.Option;
import com.vestie.vestie.survey.domain.Question;
import com.vestie.vestie.survey.domain.SubjectiveQuestion;
import com.vestie.vestie.survey.domain.Survey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@Builder
public record SurveyRegisterCommand(
        Long memberId,
        String title,
        String description,
        LocalDateTime endDate,
        List<QuestionRegisterCommand> questionCommands
) {

    public static abstract class QuestionRegisterCommand {

        protected final String title;

        public QuestionRegisterCommand(String title) {
            this.title = title;
        }

        public String title() {
            return title;
        }

        public abstract Question toDomain();
    }

    public static class MultipleChoiceQuestionCommand extends QuestionRegisterCommand {

        private final ChoiceConstraints choiceConstraints;
        private final List<String> optionInfos = new ArrayList<>();

        public MultipleChoiceQuestionCommand(
                String title,
                ChoiceConstraints choiceConstraints,
                List<String> optionInfos
        ) {
            super(title);
            this.choiceConstraints = choiceConstraints;
            this.optionInfos.addAll(optionInfos);
        }

        @Override
        public Question toDomain() {
            List<Option> options = optionInfos.stream()
                    .map(Option::new)
                    .toList();
            return new MultipleChoiceQuestion(
                    title,
                    choiceConstraints,
                    options
            );
        }
    }

    public static class SubjectiveQuestionCommand extends QuestionRegisterCommand {

        public SubjectiveQuestionCommand(String title) {
            super(title);
        }

        @Override
        public Question toDomain() {
            return new SubjectiveQuestion(title);
        }
    }

    public Survey toDomain(Member member) {
        List<Question> questions = questionCommands.stream()
                .map(QuestionRegisterCommand::toDomain)
                .toList();
        return Survey.builder()
                .member(member)
                .title(title)
                .description(description)
                .endDate(endDate)
                .questions(questions)
                .build();
    }
}
