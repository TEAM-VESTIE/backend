package com.vestie.vestie.survey.domain;


import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

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
    public List<Survey> findAll() {
        List<Survey> surveyList = new ArrayList<>();
        for(Long key : store.keySet()) {
            Survey survey = store.get(key);
            surveyList.add(survey);
        }
        return surveyList;
    }

    @Override
    public Optional<Survey> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
