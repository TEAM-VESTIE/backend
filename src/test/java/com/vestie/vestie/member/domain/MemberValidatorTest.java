package com.vestie.vestie.member.domain;

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

@DisplayName("MemberValidator 은(는)")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class MemberValidatorTest {

    private final MemberRepository memberRepository = new FakeMemberRepository();
    private final MemberValidator validator = new MemberValidator(memberRepository);

    @Nested
    class 아이디_중복검사_시 {

        @Test
        void 해당_아이디로_가입한_회원이_있으면_예외() {
            // given
            Member 동훈 = 동훈();
            memberRepository.save(동훈);

            // when
            BaseExceptionType baseExceptionType = assertThrows(MemberException.class, () ->
                    validator.validateDuplicateUsername(동훈.username())
            ).exceptionType();

            // then
            assertThat(baseExceptionType).isEqualTo(DUPLICATE_USERNAME);
        }

        @Test
        void 해당_아이디로_가입한_회원이_없으면_예외가_발생하지_않는다() {
            // given
            String uniqueUsername = "유일한 아이디";

            // when & then
            assertDoesNotThrow(() ->
                    validator.validateDuplicateUsername(uniqueUsername));
        }
    }
}
