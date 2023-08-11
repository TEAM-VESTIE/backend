package com.vestie.vestie.acceptance.survey;

import static com.vestie.vestie.acceptance.common.CommonAcceptanceSteps.*;
import static com.vestie.vestie.acceptance.member.MemberAcceptanceSteps.로그인_요청;
import static com.vestie.vestie.acceptance.member.MemberAcceptanceSteps.로그인_응답에서_액세스_토큰을_추출한다;
import static com.vestie.vestie.acceptance.member.MemberAcceptanceSteps.회원_가입_요청;
import static com.vestie.vestie.acceptance.survey.SurveyAcceptanceSteps.*;

import com.vestie.vestie.acceptance.common.AcceptanceTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("설문 인수 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class SurveyAcceptanceTest extends AcceptanceTest {

    @Nested
    class 설문_등록_시 {

        @Test
        void 설문이_등록된다() {
            // when
            회원_가입_요청("mallang", "1234", "동훈");
            var 액세스_토큰 = 로그인_응답에서_액세스_토큰을_추출한다(로그인_요청("mallang", "1234"));
            var 설문_등록_응답 = 설문_등록_요청(액세스_토큰,
                    "Vestie 설문조사",
                    "Vestie 에 대한 설문 조사입니다.",
                    N일_후(10),
                    List.of(
                            주관식_질문("이름을 알려주세요"),
                            단일_선택_객관식_질문("몇학년인가요?",
                                    "1학년",
                                    "2학년",
                                    "3학년",
                                    "4학년",
                                    "기타"),
                            복수_선택_객관식_질문("취미가 무엇인가요? (복수 선택 가능)",
                                    "게임",
                                    "코딩",
                                    "여행",
                                    "기타"
                            ),
                            주관식_질문("재학중인 학과를 입력해주세요"),
                            주관식_질문("MBTI를 입력해주세요")
                    ));

            // then
            응답_상태를_검증한다(설문_등록_응답, 생성됨);
        }
    }

    @Nested
    class 설문_조회_시 {

        @Test
        void 설문_하나가_조회된다() {
            //given
            회원_가입_요청("mallang", "1234", "동훈");
            var 액세스_토큰 = 로그인_응답에서_액세스_토큰을_추출한다(로그인_요청("mallang", "1234"));
            var 설문_등록_응답 = 설문_등록_요청(액세스_토큰,
                    "Vestie 설문조사",
                    "Vestie 에 대한 설문 조사입니다.",
                    N일_후(10),
                    List.of(
                            주관식_질문("이름을 알려주세요"),
                            단일_선택_객관식_질문("몇학년인가요?",
                                    "1학년",
                                    "2학년",
                                    "3학년",
                                    "4학년",
                                    "기타"),
                            복수_선택_객관식_질문("취미가 무엇인가요? (복수 선택 가능)",
                                    "게임",
                                    "코딩",
                                    "여행",
                                    "기타"
                            ),
                            주관식_질문("재학중인 학과를 입력해주세요"),
                            주관식_질문("MBTI를 입력해주세요")
                    ));

            // when
            Long 설문_ID = 설문_등록_응답에서_설문_ID를_추출한다(설문_등록_응답);
            var 설문_조회_응답 = 설문_조회_요청(액세스_토큰, 설문_ID);

            // then
            응답_상태를_검증한다(설문_조회_응답, 정상_처리);
        }

        @Test
        void 전체_설문이_조회된다() {
            // given
            회원_가입_요청("mallang", "1234", "동훈");
            var 액세스_토큰 = 로그인_응답에서_액세스_토큰을_추출한다(로그인_요청("mallang", "1234"));
            설문_등록_요청(액세스_토큰,
                    "Vestie 설문조사",
                    "Vestie 에 대한 설문 조사입니다.",
                    N일_후(10),
                    List.of(
                            주관식_질문("이름을 알려주세요"),
                            단일_선택_객관식_질문("몇학년인가요?",
                                    "1학년",
                                    "2학년",
                                    "3학년",
                                    "4학년",
                                    "기타"),
                            복수_선택_객관식_질문("취미가 무엇인가요? (복수 선택 가능)",
                                    "게임",
                                    "코딩",
                                    "여행",
                                    "기타"
                            ),
                            주관식_질문("재학중인 학과를 입력해주세요"),
                            주관식_질문("MBTI를 입력해주세요")
                    ));
            설문_등록_요청(액세스_토큰,
                    "Vestie 설문조사",
                    "Vestie 에 대한 설문 조사입니다.",
                    N일_후(10),
                    List.of(
                            주관식_질문("이름을 알려주세요"),
                            단일_선택_객관식_질문("몇학년인가요?",
                                    "1학년",
                                    "2학년",
                                    "3학년",
                                    "4학년",
                                    "기타"),
                            복수_선택_객관식_질문("취미가 무엇인가요? (복수 선택 가능)",
                                    "게임",
                                    "코딩",
                                    "여행",
                                    "기타"
                            ),
                            주관식_질문("재학중인 학과를 입력해주세요"),
                            주관식_질문("MBTI를 입력해주세요")
                    ));
            // when
            var 설문_조회_응답 = 전체_설문_조회_요청();

            // then
            응답_상태를_검증한다(설문_조회_응답, 정상_처리);
        }
    }
}
