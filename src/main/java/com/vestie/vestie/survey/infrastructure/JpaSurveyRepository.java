package com.vestie.vestie.survey.infrastructure;

import com.vestie.vestie.survey.domain.Survey;
import com.vestie.vestie.survey.domain.SurveyRepository;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSurveyRepository extends SurveyRepository, JpaRepository<Survey, Long> {

    @Nonnull
    @Override
    default Survey getById(Long id) {
        return SurveyRepository.super.getById(id);
    }
}
