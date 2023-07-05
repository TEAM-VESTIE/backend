package com.vestie.vestie.acceptance.survey;

import com.vestie.vestie.acceptance.common.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.vestie.vestie.acceptance.common.CommonAcceptanceSteps.생성됨;
import static com.vestie.vestie.acceptance.common.CommonAcceptanceSteps.응답_상태를_검증한다;
import static com.vestie.vestie.acceptance.member.MemberAcceptanceSteps.회원_가입_요청;
import static com.vestie.vestie.acceptance.survey.SurveyAcceptanceSteps.설문_등록_요청;

@DisplayName("설문 인수 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class SurveyAcceptanceTest extends AcceptanceTest {

    @Nested
    class 설문_등록_시 {

        @Test
        void 설문이_등록된다 () {
            // when
            회원_가입_요청("mallang", "1234", "동훈");
            var 설문_등록_응답 = 설문_등록_요청("http://vestie.vestie", LocalDateTime.now());

            // then
            응답_상태를_검증한다(설문_등록_응답, 생성됨);
        }
    }
}
