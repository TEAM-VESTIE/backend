package com.vestie.vestie;

import com.vestie.vestie.survey.domain.Survey;
import com.vestie.vestie.survey.domain.SurveyRepository;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeSurveyRepository implements SurveyRepository {

    private Map<Long, Survey> store = new HashMap<>();
    private Long id = 1L;

    @Override
    public Survey save(Survey survey) {
        ReflectionTestUtils.setField(survey, "id", id);
        return store.put(id++, survey);
    }

    @Override
    public Optional<Survey> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
