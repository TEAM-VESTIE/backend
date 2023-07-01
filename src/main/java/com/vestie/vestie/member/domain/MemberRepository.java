package com.vestie.vestie.member.domain;

import static com.vestie.vestie.member.exception.MemberExceptionType.NOT_FOUND_MEMBER;

import com.vestie.vestie.member.exception.MemberException;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    boolean existsByUsername(String username);

    Optional<Member> findById(Long id);

    default Member getById(Long id) {
        return findById(id)
                .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));
    }
}
