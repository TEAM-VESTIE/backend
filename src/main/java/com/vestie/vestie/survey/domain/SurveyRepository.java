package com.vestie.vestie.survey.domain;

import static com.vestie.vestie.survey.exception.SurveyExceptionType.NOT_FOUND_SURVEY;

import com.vestie.vestie.survey.exception.SurveyException;

import java.util.List;
import java.util.Optional;


public interface SurveyRepository {

    Survey save(Survey survey);

    List<Survey> findAll();

    Optional<Survey> findById(Long id);

    default Survey getById(Long id) {
        return findById(id)
                .orElseThrow(() -> new SurveyException(NOT_FOUND_SURVEY));
    }
}
