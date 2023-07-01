package com.vestie.vestie.member.exception;

import com.vestie.vestie.common.exception.BaseException;
import com.vestie.vestie.common.exception.BaseExceptionType;

public class MemberException extends BaseException {

    private final MemberExceptionType exceptionType;

    public MemberException(MemberExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
