package com.vestie.vestie.survey.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.JOINED;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "format")
@NoArgsConstructor(access = PROTECTED)
public abstract class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    public Question(String title) {
        this.title = title;
    }

    public Long id() {
        return id;
    }

    public String title() {
        return title;
    }
}
