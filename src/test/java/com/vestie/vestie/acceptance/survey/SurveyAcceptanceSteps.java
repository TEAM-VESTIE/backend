package com.vestie.vestie.acceptance.survey;

import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@SuppressWarnings("NonAsciiCharacters")
public class SurveyAcceptanceSteps {

    public static ExtractableResponse<Response> 설문_등록_요청 (
            String 폼링크,
            LocalDateTime 마감일
    ) {

        SurveyRegisterRequest 설문_등록_요청_데이터 = new SurveyRegisterRequest(폼링크, 마감일);
        return given()
                .body(설문_등록_요청_데이터)
                .contentType(JSON)
                .when()
                .post("/surveys")
                .then().log().all()
                .extract();
    }
}
