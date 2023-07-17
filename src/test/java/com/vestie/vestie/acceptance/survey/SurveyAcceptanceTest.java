package com.vestie.vestie.acceptance.survey;

import com.vestie.vestie.acceptance.common.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

import static com.vestie.vestie.acceptance.common.CommonAcceptanceSteps.*;
import static com.vestie.vestie.acceptance.member.MemberAcceptanceSteps.로그인_요청;
import static com.vestie.vestie.acceptance.member.MemberAcceptanceSteps.회원_가입_요청;
import static com.vestie.vestie.acceptance.survey.SurveyAcceptanceSteps.*;

@DisplayName("설문 인수 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class SurveyAcceptanceTest extends AcceptanceTest {

    @Nested
    class 설문_등록_시 {

        @Test
        void 설문이_등록된다 () {
            // given
            회원_가입_요청("mallang", "1234", "동훈");
            var 로그인_응답 = 로그인_요청("mallang", "1234");
            String 토큰 = 로그인_응답.jsonPath().getString("token");

            // when
            var 설문_등록_응답 = 설문_등록_요청(토큰, "베스티 선호도 설문조사", "http://vestie.vestie", 현재시간);

            // then
            응답_상태를_검증한다(설문_등록_응답, 생성됨);
        }
    }

    @Nested
    class 설문_조회_시 {

        @Test
        void 설문_하나가_조회된다 () {
            // given
            회원_가입_요청("mallang", "1234", "동훈");
            var 로그인_응답 = 로그인_요청("mallang", "1234");
            String 토큰 = 로그인_응답.jsonPath().getString("token");
            설문_등록_요청(토큰, "베스티 선호도 설문조사", "http://vestie.vestie", 현재시간);

            // when
            var 설문_조회_응답 = 설문_조회_요청(1L);

            // then
            응답_상태를_검증한다(설문_조회_응답, 정상_처리);
        }

        @Test
        void 전체_설문이_조회된다 () {
            // given
            회원_가입_요청("mallang", "1234", "동훈");
            var 로그인_응답 = 로그인_요청("mallang", "1234");
            String 토큰 = 로그인_응답.jsonPath().getString("token");
            설문_등록_요청(토큰, "베스티 선호도 설문조사", "http://vestie.vestie", 현재시간);
            설문_등록_요청(토큰, "베스티 선호도 설문조사2", "http://vestie.vestie", 현재시간);
            System.out.println("설문 등록");

            // when
            var 설문_조회_응답 = 전체_설문_조회_요청();

            // then
            응답_상태를_검증한다(설문_조회_응답, 정상_처리);
        }
    }
}
