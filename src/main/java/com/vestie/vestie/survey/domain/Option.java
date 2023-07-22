package com.vestie.vestie.survey.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Option {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "option_id")
    private Long id;

    private String name;

    public String name() {
        return name;
    }
}
