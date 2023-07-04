package com.vestie.vestie.member.fixture;

import com.vestie.vestie.member.domain.Member;

@SuppressWarnings("NonAsciiCharacters")
public class MemberFixture {

    public static final String 동훈_아이디 = "mallang";
    public static final String 동훈_비밀번호 = "1234";
    public static final String 동훈_이름 = "신동훈";

    public static Member 동훈() {
        return Member.builder()
                .username(동훈_아이디)
                .password(동훈_비밀번호)
                .name(동훈_이름)
                .build();
    }
}
