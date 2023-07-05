package com.vestie.vestie.survey.application;

import com.vestie.vestie.survey.domain.FakeSurveyRepository;
import com.vestie.vestie.member.domain.FakeMemberRepository;
import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.survey.application.dto.SurveyRegisterCommand;
import com.vestie.vestie.survey.domain.SurveyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.vestie.vestie.member.fixture.MemberFixture.동훈;
import static com.vestie.vestie.survey.fixture.SurveyFixture.설문_종료일;
import static com.vestie.vestie.survey.fixture.SurveyFixture.설문_폼링크;
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
            SurveyRegisterCommand surveyRegisterCommand = new SurveyRegisterCommand(회원.id(), 설문_폼링크, 설문_종료일);

            // when
            Long registeredId = surveyService.register(surveyRegisterCommand);

            // then
            assertThat(surveyRepository.findById(registeredId).get().member())
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(회원);
        }
    }
}