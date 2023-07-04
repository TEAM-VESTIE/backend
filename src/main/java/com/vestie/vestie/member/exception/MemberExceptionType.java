package com.vestie.vestie.member.exception;

import com.vestie.vestie.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MemberExceptionType implements BaseExceptionType {

    NOT_FOUND_MEMBER(100, HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다."),
    DUPLICATE_USERNAME(101, HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    AUTHENTICATION_FAIL(102, HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다."),
    ;

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    MemberExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
