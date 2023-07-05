package com.vestie.vestie.auth.infrastructure.jwt;

import static com.vestie.vestie.auth.execption.AuthExceptionType.INVALID_ACCESS_TOKEN;
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
import com.vestie.vestie.common.exception.BaseExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayName("JwtAccessTokenDecoder 은(는)")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class JwtAccessTokenDecoderTest {

    private final Algorithm algorithm = Algorithm.HMAC512("dmVzdGllYmFja2VuZHNlY3JldA==");
    private final AccessTokenProvider provider = new JwtAccessTokenProvider(
            algorithm,
            MILLISECONDS.convert(1, DAYS));
    private final AccessTokenDecoder decoder = new JwtAccessTokenDecoder(algorithm);

    @Test
    void 액세스_토큰으로부터_클레임을_추출한다() {
        // given
        AccessToken accessToken = provider.provide(Claims.fromId(1L));

        // when
        Claims claims = decoder.decode(accessToken.value());

        // then
        assertThat(claims.getId()).isEqualTo(1L);
    }

    @Test
    void 액세스_토큰이_잘못되었다면_오류() {
        // when
        BaseExceptionType baseExceptionType = assertThrows(AuthException.class, () ->
                decoder.decode("wrong")
        ).exceptionType();

        // then
        assertThat(baseExceptionType).isEqualTo(INVALID_ACCESS_TOKEN);
    }
}
