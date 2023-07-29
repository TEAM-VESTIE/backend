package com.vestie.vestie.acceptance.survey;

import static com.vestie.vestie.acceptance.common.CommonAcceptanceSteps.given;
import static com.vestie.vestie.survey.domain.MultipleChoiceQuestion.ChoiceConstraints.MULTIPLE;
import static com.vestie.vestie.survey.domain.MultipleChoiceQuestion.ChoiceConstraints.SINGLE;
import static io.restassured.http.ContentType.JSON;

import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest;
import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest.MultipleChoiceQuestionRequest;
import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest.QuestionRegisterRequest;
import com.vestie.vestie.survey.presentation.dto.SurveyRegisterRequest.SubjectiveQuestionRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class SurveyAcceptanceSteps {

    public static MultipleChoiceQuestionRequest 단일_선택_객관식_질문(String 질문, String... 옵션들) {
        return new MultipleChoiceQuestionRequest("MULTIPLE_CHOICE", 질문, SINGLE, Arrays.asList(옵션들));
    }

    public static MultipleChoiceQuestionRequest 복수_선택_객관식_질문(String 질문, String... 옵션들) {
        return new MultipleChoiceQuestionRequest("MULTIPLE_CHOICE", 질문, MULTIPLE, Arrays.asList(옵션들));
    }

    public static SubjectiveQuestionRequest 주관식_질문(String 질문) {
        return new SubjectiveQuestionRequest("SUBJECTIVE", 질문);
    }

    public static ExtractableResponse<Response> 설문_등록_요청(
            String 액세스_토큰,
            String 제목,
            String 설명,
            LocalDateTime 마감일,
            List<QuestionRegisterRequest> 질문들
    ) {
        SurveyRegisterRequest 설문_등록_요청_데이터 = new SurveyRegisterRequest(제목, 설명, 마감일, 질문들);
        return given(액세스_토큰)
                .body(설문_등록_요청_데이터)
                .contentType(JSON)
                .when()
                .post("/surveys")
                .then().log().all()
                .extract();
    }

//    public static ExtractableResponse<Response> 설문_조회_요청(
//            Long id
//    ) {
//        return given()
//                .contentType(JSON)
//                .when()
//                .get("/surveys/" + id)
//                .then().log().all()
//                .extract();
//    }
//
//    public static ExtractableResponse<Response> 전체_설문_조회_요청() {
//        return given()
//                .when()
//                .get("/surveys")
//                .then().log().all()
//                .extract();
//    }
}
