package com.vestie.vestie.survey.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;


@DisplayName("Survey 은(는)")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class SurveyTest {

    private final SurveyRepository repository = new FakeSurveyRepository();

    @Nested
    class 설문등록_시 {

    }
}