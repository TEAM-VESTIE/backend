package com.vestie.vestie.auth.presentation;

import static com.vestie.vestie.auth.execption.AuthExceptionType.NOT_FOUND_ACCESS_TOKEN;

import com.vestie.vestie.auth.domain.AccessTokenDecoder;
import com.vestie.vestie.auth.domain.Claims;
import com.vestie.vestie.auth.execption.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AccessTokenDecoder decoder;
    private final AuthenticationContext context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = getAccessToken(request);
        Claims claims = decoder.decode(token);
        context.setPrincipal(claims.getId());
        return true;
    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String[] token = header.split("Bearer ");
        validateTokenExist(token);
        return token[1];
    }

    private void validateTokenExist(String[] token) {
        if (token.length != 2) {
            throw new AuthException(NOT_FOUND_ACCESS_TOKEN);
        }
    }
}
