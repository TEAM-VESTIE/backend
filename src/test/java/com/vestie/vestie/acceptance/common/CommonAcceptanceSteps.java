package com.vestie.vestie.acceptance.common;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import com.vestie.vestie.common.exception.BaseExceptionType;
import com.vestie.vestie.common.exception.ExceptionResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

@SuppressWarnings("NonAsciiCharacters")
public class CommonAcceptanceSteps {

    public static HttpStatus 정상_처리 = HttpStatus.OK;
    public static HttpStatus 생성됨 = HttpStatus.CREATED;
    public static HttpStatus 잘못된_요청 = HttpStatus.BAD_REQUEST;
    public static HttpStatus 권한_없음 = HttpStatus.FORBIDDEN;
    public static HttpStatus 찾을수_없음 = HttpStatus.NOT_FOUND;
    public static HttpStatus 중복됨 = HttpStatus.CONFLICT;

    public static LocalDateTime 현재시간 = LocalDateTime.now();

    public static LocalDateTime N일_후(int 며칠) {
        return LocalDateTime.now().plusDays(며칠);
    }

    public static RequestSpecification given() {
        return RestAssured
                .given().log().all()
                .contentType(JSON);
    }

    public static RequestSpecification given(String 액세스_토큰) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + 액세스_토큰)
                .log().all()
                .contentType(JSON);
    }

    public static void 응답_상태를_검증한다(ExtractableResponse<Response> 응답, HttpStatus 상태) {
        assertThat(응답.statusCode())
                .isEqualTo(상태.value());
    }

    public static void 발생한_예외를_검증한다(
            ExtractableResponse<Response> 응답,
            BaseExceptionType 예외_타입
    ) {
        ExceptionResponse exceptionResponse = 응답.as(ExceptionResponse.class);
        assertThat(exceptionResponse.code())
                .isEqualTo(String.valueOf(예외_타입.errorCode()));
        assertThat(exceptionResponse.message())
                .isEqualTo(예외_타입.errorMessage());
    }

    public static <T> void 값이_존재한다(T t) {
        assertThat(t).isNotNull();
    }

    public static <T> void 두_값이_같다(T 첫번째_값, T 두번째_값) {
        assertThat(첫번째_값).isEqualTo(두번째_값);
    }
}
