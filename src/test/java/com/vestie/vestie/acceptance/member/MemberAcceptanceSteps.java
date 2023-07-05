package com.vestie.vestie.acceptance.member;

import static com.vestie.vestie.acceptance.common.CommonAcceptanceSteps.given;

import com.vestie.vestie.member.presentation.dto.LoginRequest;
import com.vestie.vestie.member.presentation.dto.LoginResponse;
import com.vestie.vestie.member.presentation.dto.SignUpRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@SuppressWarnings("NonAsciiCharacters")
public class MemberAcceptanceSteps {

    public static ExtractableResponse<Response> 회원_가입_요청(
            String 아이디,
            String 비밀번호,
            String 이름
    ) {
        SignUpRequest 회원_가입_요청_데이터 = new SignUpRequest(아이디, 비밀번호, 이름);
        return given()
                .body(회원_가입_요청_데이터)
                .when()
                .post("/members")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인_요청(
            String 아이디,
            String 비밀번호
    ) {
        LoginRequest 로그인_요청_데이터 = new LoginRequest(아이디, 비밀번호);
        return given()
                .body(로그인_요청_데이터)
                .when()
                .post("/members/login")
                .then().log().all()
                .extract();
    }

    public static String 액세스_토큰을_추출한다(
            ExtractableResponse<Response> 응답
    ) {
        LoginResponse 로그인_응답 = 응답.body().as(LoginResponse.class);
        return 로그인_응답.token();
    }
}
