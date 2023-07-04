package com.vestie.vestie.member.application;

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

    public void signUp(SignUpCommand command) {
        Member member = command.toDomain();
        member.signUp(memberValidator);
        memberRepository.save(member);
    }
}
