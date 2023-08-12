package com.vestie.vestie.survey.domain;


import com.vestie.vestie.survey.exception.SurveyException;
import com.vestie.vestie.survey.exception.SurveyExceptionType;

public enum QuestionType {
    SUBJECTIVE(Values.SUBJECTIVE),
    MULTIPLE_CHOICE(Values.MULTIPLE_CHOICE);

    QuestionType(String val) {
        if(!this.name().equals(val))
            throw new SurveyException(SurveyExceptionType.NOT_FOUND_SURVEY_QUESTION_TYPE);
    }

    public static class Values {
        public static final String SUBJECTIVE = "SUBJECTIVE";
        public static final String MULTIPLE_CHOICE = "MULTIPLE_CHOICE";
    }
}
