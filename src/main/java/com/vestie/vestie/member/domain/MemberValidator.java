package com.vestie.vestie.member.domain;

import static com.vestie.vestie.member.exception.MemberExceptionType.DUPLICATE_USERNAME;

import com.vestie.vestie.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validateDuplicateUsername(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new MemberException(DUPLICATE_USERNAME);
        }
    }
}
