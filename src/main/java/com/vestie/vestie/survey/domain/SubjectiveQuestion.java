package com.vestie.vestie.survey.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SUBJECTIVE")
@NoArgsConstructor(access = PROTECTED)
public class SubjectiveQuestion extends Question {

    public SubjectiveQuestion(String title) {
        super(title);
    }
}
