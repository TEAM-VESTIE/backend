package com.vestie.vestie.member.application;

import com.vestie.vestie.auth.domain.AccessToken;
import com.vestie.vestie.auth.domain.AccessTokenProvider;
import com.vestie.vestie.auth.domain.Claims;
import com.vestie.vestie.member.application.dto.LoginCommand;
import com.vestie.vestie.member.application.dto.LoginResult;
import com.vestie.vestie.member.application.dto.SignUpCommand;
import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.member.domain.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final AccessTokenProvider accessTokenProvider;

    public void signUp(SignUpCommand command) {
        Member member = command.toDomain();
        member.signUp(memberValidator);
        memberRepository.save(member);
    }

    public LoginResult login(LoginCommand command) {
        Member member = memberRepository.getByUsername(command.username());
        member.login(command.password());
        AccessToken token = accessTokenProvider.provide(Claims.fromId(member.id()));
        return new LoginResult(token, member.name());
    }
}
