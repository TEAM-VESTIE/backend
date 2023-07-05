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
    public List<Survey> findAllByMemberId(Long memberId) {
        List<Survey> surveyList = new ArrayList<>();
        for(int i=0; i<store.size(); i++) {
            Survey s = store.get(i);
            if(s.member().id() == memberId) {
                surveyList.add(s);
            }
        }
        return surveyList;
    }

    @Override
    public Optional<Survey> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
