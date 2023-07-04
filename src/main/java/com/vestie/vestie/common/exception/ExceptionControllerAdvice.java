package com.vestie.vestie.common.exception;

import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final String UNEXPECTED_ERROR_CODE = "1";
    private static final String BAD_REQUEST_ERROR_CODE = "2";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException e) {
        String errorMessage = e.getFieldErrors().stream()
                .map(it -> it.getField() + " : " + it.getDefaultMessage())
                .collect(Collectors.joining("\n"));
        log.warn("요청 필드의 형식이 올바르지 않습니다. [%s]".formatted(errorMessage));
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                BAD_REQUEST_ERROR_CODE,
                "요청 필드의 형식이 올바르지 않습니다. [%s]".formatted(errorMessage));
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(BaseException.class)
    ResponseEntity<ExceptionResponse> handleException(BaseException e) {
        BaseExceptionType type = e.exceptionType();
        log.warn("[WARN] 예외 내용: {}", type.errorMessage());
        return new ResponseEntity<>(
                new ExceptionResponse(String.valueOf(type.errorCode()), type.errorMessage()),
                type.httpStatus());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error("[ERROR] 원인을 알 수 없는 예외", e);
        return ResponseEntity.internalServerError()
                .body(new ExceptionResponse(
                        UNEXPECTED_ERROR_CODE,
                        "알 수 없는 오류가 발생했습니다."));
    }
}
