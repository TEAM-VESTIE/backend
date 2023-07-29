package com.vestie.vestie.member.domain;

import static com.vestie.vestie.member.exception.MemberExceptionType.NOT_FOUND_MEMBER;

import com.vestie.vestie.member.exception.MemberException;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    boolean existsByUsername(String username);

    Member getById(Long id);

    Optional<Member> findByUsername(String username);

    default Member getByUsername(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));
    }
}
