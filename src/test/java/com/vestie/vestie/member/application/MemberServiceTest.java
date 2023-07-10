package com.vestie.vestie.member.application;

import static com.vestie.vestie.member.exception.MemberExceptionType.AUTHENTICATION_FAIL;
import static com.vestie.vestie.member.exception.MemberExceptionType.DUPLICATE_USERNAME;
import static com.vestie.vestie.member.exception.MemberExceptionType.NOT_FOUND_MEMBER;
import static com.vestie.vestie.member.fixture.MemberFixture.동훈;
import static com.vestie.vestie.member.fixture.MemberFixture.동훈_비밀번호;
import static com.vestie.vestie.member.fixture.MemberFixture.동훈_아이디;
import static com.vestie.vestie.member.fixture.MemberFixture.동훈_이름;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.vestie.vestie.auth.FakeAccessTokenProvider;
import com.vestie.vestie.auth.domain.AccessTokenProvider;
import com.vestie.vestie.common.exception.BaseExceptionType;
import com.vestie.vestie.member.application.dto.LoginCommand;
import com.vestie.vestie.member.application.dto.LoginResult;
import com.vestie.vestie.member.application.dto.SignUpCommand;
import com.vestie.vestie.member.domain.FakeMemberRepository;
import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.member.domain.MemberValidator;
import com.vestie.vestie.member.exception.MemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("MemberService 은(는)")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class MemberServiceTest {

    private final AccessTokenProvider accessTokenProvider = new FakeAccessTokenProvider();
    private final MemberRepository memberRepository = new FakeMemberRepository();
    private final MemberValidator memberValidator = new MemberValidator(memberRepository);
    private final MemberService memberService = new MemberService(memberRepository, memberValidator,
            accessTokenProvider);

    @Nested
    class 회원가입_시 {

        @Test
        void 중복된_아이디가_있으면_오류() {
            // given
            Member 동훈 = 동훈();
            memberRepository.save(동훈);
            SignUpCommand command = new SignUpCommand(동훈.username(), "password", "name");

            // when
            BaseExceptionType baseExceptionType = assertThrows(MemberException.class, () ->
                    memberService.signUp(command)
            ).exceptionType();

            // then
            assertThat(baseExceptionType).isEqualTo(DUPLICATE_USERNAME);
        }

        @Test
        void 아이디가_중복되지_않으면_생성된다() {
            // given
            SignUpCommand command = new SignUpCommand(동훈_아이디, 동훈_비밀번호, 동훈_이름);

            // when & then
            assertDoesNotThrow(() -> memberService.signUp(command));
            assertThat(memberRepository.findById(1L))
                    .isPresent();
        }
    }

    @Nested
    class 로그인_시 {

        private final String username = "말랑";
        private final String password = "1234";

        @BeforeEach
        void setUp() {
            memberService.signUp(new SignUpCommand(username, password, "동훈"));
        }

        @Test
        void 로그인에_성공하면_AccessToken을_발급하고_이름과_함께_반환한다() {
            // given
            LoginCommand command = new LoginCommand(username, password);

            // when
            LoginResult result = memberService.login(command);

            // then
            assertThat(result.accessToken()).isNotNull();
            assertThat(result.name()).isEqualTo("동훈");
        }

        @Test
        void 아이디가_없으면_예외() {
            // given
            LoginCommand command = new LoginCommand("wrong", password);

            // when
            BaseExceptionType baseExceptionType = assertThrows(MemberException.class, () ->
                    memberService.login(command)
            ).exceptionType();

            // then
            assertThat(baseExceptionType).isEqualTo(NOT_FOUND_MEMBER);
        }

        @Test
        void 비밀번호가_없으면_예외() {
            // given
            LoginCommand command = new LoginCommand(username, "wrong");

            // when
            BaseExceptionType baseExceptionType = assertThrows(MemberException.class, () ->
                    memberService.login(command)
            ).exceptionType();

            // then
            assertThat(baseExceptionType).isEqualTo(AUTHENTICATION_FAIL);
        }
    }
}
