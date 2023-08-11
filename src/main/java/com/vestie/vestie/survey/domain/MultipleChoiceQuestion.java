package com.vestie.vestie.survey.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value=QuestionType.Values.MULTIPLE_CHOICE)
@NoArgsConstructor(access = PROTECTED)
public class MultipleChoiceQuestion extends Question {

    @Enumerated(STRING)
    private ChoiceConstraints choiceConstraints;

    @OneToMany(cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "multiple_choice_question_id", nullable = false, updatable = false)
    private List<Option> options = new ArrayList<>();

    public MultipleChoiceQuestion(
            String title,
            ChoiceConstraints choiceConstraints,
            List<Option> options
    ) {
        super(title);
        this.choiceConstraints = choiceConstraints;
        this.options = options;
    }

    public enum ChoiceConstraints {
        SINGLE,
        MULTIPLE;
    }

    public ChoiceConstraints choiceConstraints() {
        return choiceConstraints;
    }

    public List<Option> options() {
        return new ArrayList<>(options);
    }
}
