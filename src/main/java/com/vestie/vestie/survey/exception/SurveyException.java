package com.vestie.vestie.survey.exception;

import com.vestie.vestie.common.exception.BaseException;
import com.vestie.vestie.common.exception.BaseExceptionType;

public class SurveyException extends BaseException {

    private final SurveyExceptionType exceptionType;

    public SurveyException(SurveyExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
