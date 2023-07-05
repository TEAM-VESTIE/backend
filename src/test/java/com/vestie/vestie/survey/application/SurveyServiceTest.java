package com.vestie.vestie.survey.application;

import com.vestie.vestie.survey.domain.FakeSurveyRepository;
import com.vestie.vestie.member.domain.FakeMemberRepository;
import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import com.vestie.vestie.survey.domain.Survey;
import com.vestie.vestie.survey.domain.SurveyRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

import static com.vestie.vestie.member.fixture.MemberFixture.동훈;
import static com.vestie.vestie.survey.fixture.SurveyFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SurveyService 은(는)")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class SurveyServiceTest {

    private final MemberRepository memberRepository = new FakeMemberRepository();
    private final SurveyRepository surveyRepository = new FakeSurveyRepository();
    private final SurveyService surveyService = new SurveyService(memberRepository, surveyRepository);

    @Nested
    class 설문등록_시 {

        @Test
        void 설문이_등록된다() {
            // given
            Member 회원 = 동훈();
            memberRepository.save(회원);
            SurveyRegisterCommand surveyRegisterCommand = new SurveyRegisterCommand(회원.id(), 제목, 설문_폼링크, 설문_종료일);

            // when
            Long registeredId = surveyService.register(surveyRegisterCommand);

            // then
            assertThat(surveyRepository.findById(registeredId).get().member())
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(회원);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 설문조회_시 {

        private Member member;
        private Survey survey;

        @BeforeAll
        void setup() {
            Member 회원 = 동훈();
            member = memberRepository.save(회원);

            // 설문 등록
            SurveyRegisterCommand surveyRegisterCommand = new SurveyRegisterCommand(member.id(), 제목, 설문_폼링크, 설문_종료일);
            survey = surveyRepository.save(surveyRegisterCommand.toDomain(member));
            surveyRegisterCommand = new SurveyRegisterCommand(member.id(), 제목+"1", 설문_폼링크, 설문_종료일);
            surveyRepository.save(surveyRegisterCommand.toDomain(member));
            surveyRegisterCommand = new SurveyRegisterCommand(member.id(), 제목+"2", 설문_폼링크, 설문_종료일);
            surveyRepository.save(surveyRegisterCommand.toDomain(member));
        }

        @Test
        void 설문_리스트가_조회된다() {
            // given

            // when
            var allSurvey = surveyService.getAllSurvey(member.id());

            // then
            assertThat(allSurvey.get(0))
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(survey);
        }

        @Test
        void 설문_하나가_조회된다() {
            // given

            // when
            var getSurvey = surveyService.getSurvey(survey.id());

            // then
            assertThat(getSurvey)
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(survey);
        }
    }
}