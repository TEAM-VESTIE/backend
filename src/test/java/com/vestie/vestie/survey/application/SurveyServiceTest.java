package com.vestie.vestie.survey.application;

import static com.vestie.vestie.member.fixture.MemberFixture.동훈;
import static com.vestie.vestie.survey.domain.MultipleChoiceQuestion.ChoiceConstraints.MULTIPLE;
import static com.vestie.vestie.survey.domain.MultipleChoiceQuestion.ChoiceConstraints.SINGLE;
import static org.assertj.core.api.Assertions.assertThat;

import com.vestie.vestie.common.DataClearExtension;
import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand.MultipleChoiceQuestionCommand;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand.SubjectiveQuestionCommand;
import com.vestie.vestie.survey.domain.MultipleChoiceQuestion;
import com.vestie.vestie.survey.domain.Option;
import com.vestie.vestie.survey.domain.Survey;
import com.vestie.vestie.survey.domain.SurveyRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@DisplayName("SurveyService 은(는)")
@ExtendWith(DataClearExtension.class)
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class SurveyServiceTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Nested
    class 설문등록_시 {

        @Test
        void 설문이_등록된다() {
            // given
            Member 회원 = 동훈();
            memberRepository.save(회원);
            SurveyRegisterCommand surveyRegisterCommand = new SurveyRegisterCommand(
                    회원.id(),
                    "Vestie 설문조사",
                    "Vestie 에 대한 설문 조사입니다.",
                    LocalDateTime.now().plusDays(10),
                    List.of(
                            new SubjectiveQuestionCommand("이름을 알려주세요"),
                            new MultipleChoiceQuestionCommand("몇학년인가요?", SINGLE, List.of(
                                    "1학년",
                                    "2학년",
                                    "3학년",
                                    "4학년",
                                    "기타"
                            )),
                            new MultipleChoiceQuestionCommand("취미가 무엇인가요? (복수 선택 가능)", MULTIPLE, List.of(
                                    "게임",
                                    "코딩",
                                    "여행",
                                    "기타"
                            )),
                            new SubjectiveQuestionCommand("재학중인 학과를 입력해주세요"),
                            new SubjectiveQuestionCommand("MBTI를 입력해주세요")
                    )
            );

            // when
            Long registeredId = surveyService.register(surveyRegisterCommand);

            // then
            Survey survey = surveyRepository.findById(registeredId).get();
            assertThat(survey.questions()).hasSize(5);
            assertThat(survey.questions()).extracting(it -> it.getClass().getSimpleName())
                    .containsExactly(
                            "SubjectiveQuestion",
                            "MultipleChoiceQuestion",
                            "MultipleChoiceQuestion",
                            "SubjectiveQuestion",
                            "SubjectiveQuestion"
                    );
            MultipleChoiceQuestion question = (MultipleChoiceQuestion) survey.questions().get(1);
            assertThat(question.options()).extracting(Option::name)
                    .containsExactly(
                            "1학년",
                            "2학년",
                            "3학년",
                            "4학년",
                            "기타"
                    );
        }
    }

//    @Nested
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
//    class 설문조회_시 {
//
//        private Member member;
//        private Survey survey;
//
//        @BeforeAll
//        void setup() {
//            Member 회원 = 동훈();
//            member = memberRepository.save(회원);
//
//            // 설문 등록
//            survey = surveyRepository.save(설문());
//            surveyRepository.save(설문());
//            surveyRepository.save(설문());
//        }
//
//        @Test
//        void 설문_리스트가_조회된다() {
//            // when
//            List<SurveyInquiryResponse> allSurvey = surveyQueryService.getAllSurvey();
//
//            // then
//            assertThat(allSurvey.get(0))
//                    .usingRecursiveComparison()
//                    .ignoringFields("id")
//                    .isEqualTo(survey);
//        }
//
//        @Test
//        void 설문_하나가_조회된다() {
//            // when
//            SurveyResponse getSurvey = surveyQueryService.getSurvey(survey.id());
//
//            // then
//            assertThat(getSurvey)
//                    .usingRecursiveComparison()
//                    .ignoringFields("id")
//                    .isEqualTo(survey);
//        }
//    }
}
