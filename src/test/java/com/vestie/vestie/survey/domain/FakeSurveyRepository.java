package com.vestie.vestie.survey.domain;


import org.springframework.test.util.ReflectionTestUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeSurveyRepository implements SurveyRepository {

    private final Map<Long, Survey> store = new HashMap<>();
    private Long id = 1L;

    @Override
    public Survey save(Survey survey) {
        ReflectionTestUtils.setField(survey, "id", id);
        store.put(id, survey);
        return store.get(id++);
    }

    @Override
    public Optional<Survey> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
