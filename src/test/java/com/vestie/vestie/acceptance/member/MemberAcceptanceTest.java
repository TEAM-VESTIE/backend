package com.vestie.vestie.acceptance.member;

import static com.vestie.vestie.acceptance.common.CommonAcceptanceSteps.생성됨;
import static com.vestie.vestie.acceptance.common.CommonAcceptanceSteps.응답_상태를_검증한다;
import static com.vestie.vestie.acceptance.common.CommonAcceptanceSteps.중복됨;
import static com.vestie.vestie.acceptance.member.MemberAcceptanceSteps.회원_가입_요청;

import com.vestie.vestie.acceptance.common.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("회원 인수 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class MemberAcceptanceTest extends AcceptanceTest {

    @Nested
    class 회원_가입_시 {

        @Test
        void 아이디가_중복되지_않으면_가입된다() {
            // when
            var 회원_가입_응답 = 회원_가입_요청("mallang", "1234", "동훈");

            // then
            응답_상태를_검증한다(회원_가입_응답, 생성됨);
        }

        @Test
        void 아이디가_중복되면_가입되지_않는다() {
            // given
            회원_가입_요청("vestie", "1234", "동훈");

            // when
            var 응답 = 회원_가입_요청("vestie", "12345", "미래");

            // then
            응답_상태를_검증한다(응답, 중복됨);
        }
    }
}
