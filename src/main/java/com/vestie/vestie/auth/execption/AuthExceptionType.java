package com.vestie.vestie.auth.execption;

import com.vestie.vestie.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum AuthExceptionType implements BaseExceptionType {

    NOT_FOUND_ACCESS_TOKEN(200, HttpStatus.UNAUTHORIZED, "액세스 토큰이 존재하지 않습니다."),
    INVALID_ACCESS_TOKEN(201, HttpStatus.UNAUTHORIZED, "액세스 토큰이 유효하지 않습니다."),
    ;

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    AuthExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
