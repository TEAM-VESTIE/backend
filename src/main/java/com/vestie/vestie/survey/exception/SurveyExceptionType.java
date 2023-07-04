package com.vestie.vestie.survey.exception;

import com.vestie.vestie.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum SurveyExceptionType implements BaseExceptionType {

    NOT_FOUND_SURVEY(200, HttpStatus.NOT_FOUND, "설문이 존재하지 않습니다."),
    ;

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    SurveyExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int errorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
