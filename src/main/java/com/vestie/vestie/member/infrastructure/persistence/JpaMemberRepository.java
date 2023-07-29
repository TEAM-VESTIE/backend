package com.vestie.vestie.member.infrastructure.persistence;

import static com.vestie.vestie.member.exception.MemberExceptionType.NOT_FOUND_MEMBER;

import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import com.vestie.vestie.member.exception.MemberException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends MemberRepository, JpaRepository<Member, Long> {

    @Override
    default Member getById(Long id) {
        return findById(id)
                .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));
    }

    @Override
    default Member getByUsername(String username) {
        return MemberRepository.super.getByUsername(username);
    }
}
