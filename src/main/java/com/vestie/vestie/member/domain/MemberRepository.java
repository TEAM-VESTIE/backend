package com.vestie.vestie.member.domain;

import static com.vestie.vestie.member.exception.MemberExceptionType.NOT_FOUND_MEMBER;

import com.vestie.vestie.member.exception.MemberException;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Nonnull
    default Member getById(Long id) {
        return findById(id)
                .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));
    }
}
