package com.vestie.vestie.member.domain;

import static com.vestie.vestie.member.exception.MemberExceptionType.AUTHENTICATION_FAIL;
import static com.vestie.vestie.member.exception.MemberExceptionType.DUPLICATE_USERNAME;
import static com.vestie.vestie.member.fixture.MemberFixture.동훈;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.vestie.vestie.common.exception.BaseExceptionType;
import com.vestie.vestie.member.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Member 은(는)")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class MemberTest {

    private final MemberRepository memberRepository = new FakeMemberRepository();
    private final MemberValidator memberValidator = new MemberValidator(memberRepository);

    @Nested
    class 회원가입_시 {

        @Test
        void 중복된_아이디가_있으면_오류() {
            // given
            Member 이미_가입한_회원 = 동훈();
            memberRepository.save(이미_가입한_회원);

            Member 가입할_회원 = 동훈();

            // when
            BaseExceptionType baseExceptionType = assertThrows(MemberException.class, () ->
                    가입할_회원.signUp(memberValidator)
            ).exceptionType();

            // then
            assertThat(baseExceptionType).isEqualTo(DUPLICATE_USERNAME);
        }

        @Test
        void 아이디가_중복되지_않으면_생성된다() {
            // given
            Member 가입할_회원 = 동훈();

            // when & then
            assertDoesNotThrow(() -> 가입할_회원.signUp(memberValidator));
        }
    }

    @Nested
    class 로그인_시 {

        @Test
        void 비밀번호가_일치하면_예외가_발생하지_않는다() {
            // given
            Member 동훈 = 동훈();

            // when & then
            assertDoesNotThrow(() ->
                    동훈.login(동훈.password()));
        }

        @Test
        void 비밀번호가_일치하지_않으면_예외이다() {
            // given
            Member 동훈 = 동훈();

            // when
            BaseExceptionType baseExceptionType = assertThrows(MemberException.class, () ->
                    동훈.login("잘못된 비밀번호")
            ).exceptionType();

            // then
            assertThat(baseExceptionType)
                    .isEqualTo(AUTHENTICATION_FAIL);
        }
    }
}
