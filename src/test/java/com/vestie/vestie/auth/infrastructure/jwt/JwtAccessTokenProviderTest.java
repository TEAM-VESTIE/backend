package com.vestie.vestie.auth.infrastructure.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import com.vestie.vestie.auth.domain.AccessToken;
import com.vestie.vestie.auth.domain.AccessTokenProvider;
import com.vestie.vestie.auth.domain.Claims;
import com.vestie.vestie.auth.domain.JwtProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayName("JwtAccessTokenProvider 은(는)")
@DisplayNameGeneration(ReplaceUnderscores.class)
class JwtAccessTokenProviderTest {

    private final JwtProperties properties = new JwtProperties(
            "dmVzdGllYmFja2VuZHNlY3JldA==",  // vestiebackendsecret -> BASE 64
            1);
    private final AccessTokenProvider accessTokenProvider = new JwtAccessTokenProvider(properties);

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
