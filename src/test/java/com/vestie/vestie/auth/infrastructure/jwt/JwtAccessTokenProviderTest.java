package com.vestie.vestie.auth.infrastructure.jwt;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.Assertions.assertThat;

import com.auth0.jwt.algorithms.Algorithm;
import com.vestie.vestie.auth.domain.AccessToken;
import com.vestie.vestie.auth.domain.AccessTokenProvider;
import com.vestie.vestie.auth.domain.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayName("JwtAccessTokenProvider 은(는)")
@DisplayNameGeneration(ReplaceUnderscores.class)
class JwtAccessTokenProviderTest {

    private final AccessTokenProvider accessTokenProvider = new JwtAccessTokenProvider(
            Algorithm.HMAC512("dmVzdGllYmFja2VuZHNlY3JldA=="),
            MILLISECONDS.convert(1, DAYS));

    @Test
    void 클레임을_통해_토큰을_생성한다() {
        // given
        Claims claims = new Claims();
        claims.addClaims("id", "1");

        // when
        AccessToken accessToken = accessTokenProvider.provide(claims);

        // then
        assertThat(accessToken.value().split("\\.")[0])
                .isEqualTo("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9");
    }
}
