package com.vestie.vestie.auth.presentation;

import static com.vestie.vestie.auth.execption.AuthExceptionType.INVALID_ACCESS_TOKEN;
import static com.vestie.vestie.auth.execption.AuthExceptionType.NOT_FOUND_ACCESS_TOKEN;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.auth0.jwt.algorithms.Algorithm;
import com.vestie.vestie.auth.domain.AccessToken;
import com.vestie.vestie.auth.domain.AccessTokenDecoder;
import com.vestie.vestie.auth.domain.AccessTokenProvider;
import com.vestie.vestie.auth.domain.Claims;
import com.vestie.vestie.auth.execption.AuthException;
import com.vestie.vestie.auth.infrastructure.jwt.JwtAccessTokenDecoder;
import com.vestie.vestie.auth.infrastructure.jwt.JwtAccessTokenProvider;
import com.vestie.vestie.common.exception.BaseExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;

@DisplayName("AuthInterceptor 은(는)")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class AuthInterceptorTest {

    private final Algorithm algorithm = Algorithm.HMAC512("dmVzdGllYmFja2VuZHNlY3JldA==");
    private final AccessTokenProvider provider = new JwtAccessTokenProvider(
            algorithm,
            MILLISECONDS.convert(1, DAYS));
    private final AccessTokenDecoder decoder = new JwtAccessTokenDecoder(algorithm);
    private final AuthenticationContext context = new AuthenticationContext();
    private final AuthInterceptor authInterceptor = new AuthInterceptor(decoder, context);

    @Test
    void 액세스_토큰의_인증정보를_인증_Context에_저장한다() {
        // given
        AccessToken token = provider.provide(Claims.fromId(1L));
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.value());

        // when
        boolean result = authInterceptor.preHandle(request, null, null);

        // then
        assertThat(result).isTrue();
        assertThat(context.principal()).isEqualTo(1L);
    }

    @Test
    void Bearer_로_시작하지_않으면_오류() {
        // given
        AccessToken token = provider.provide(Claims.fromId(1L));
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, token.value());

        // when
        BaseExceptionType baseExceptionType = assertThrows(AuthException.class, () ->
                authInterceptor.preHandle(request, null, null)
        ).exceptionType();

        // then
        assertThat(baseExceptionType).isEqualTo(NOT_FOUND_ACCESS_TOKEN);
        assertThat(context.principal()).isNull();
    }

    @Test
    void 토큰이_잘못된_경우_오류() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer invalid");

        // when
        BaseExceptionType baseExceptionType = assertThrows(AuthException.class, () ->
                authInterceptor.preHandle(request, null, null)
        ).exceptionType();

        // then
        assertThat(baseExceptionType).isEqualTo(INVALID_ACCESS_TOKEN);
        assertThat(context.principal()).isNull();
    }
}
