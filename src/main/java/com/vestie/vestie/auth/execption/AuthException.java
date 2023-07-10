package com.vestie.vestie.auth.execption;

import com.vestie.vestie.common.exception.BaseException;
import com.vestie.vestie.common.exception.BaseExceptionType;

public class AuthException extends BaseException {

    private final AuthExceptionType exceptionType;

    public AuthException(AuthExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
