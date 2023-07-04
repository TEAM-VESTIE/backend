package com.vestie.vestie.survey.fixture;

import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.fixture.MemberFixture;
import com.vestie.vestie.survey.domain.Survey;

import java.time.LocalDateTime;

@SuppressWarnings("NonAsciiCharacters")
public class SurveyFixture {

    public static final String 설문_폼링크 = "http://vestie.vestie";
    public static final LocalDateTime 설문_종료일 = LocalDateTime.now();
    public static final Member 동훈 = MemberFixture.동훈();

    public static Survey 설문() {
        return Survey.builder()
                .formLink(설문_폼링크)
                .endDate(설문_종료일)
                .member(동훈)
                .build();
    }
}
