//package com.vestie.vestie.survey.fixture;
//
//import com.vestie.vestie.member.domain.Member;
//import com.vestie.vestie.member.fixture.MemberFixture;
//import com.vestie.vestie.survey.domain.Survey;
//import java.time.LocalDateTime;
//
//@SuppressWarnings("NonAsciiCharacters")
//public class SurveyFixture {
//
//    public static final String 제목 = "베스티 선호도 설문 조사";
//    public static final LocalDateTime 설문_종료일 = LocalDateTime.now();
//    public static final Member 동훈 = MemberFixture.동훈();
//
//    public static Survey 설문() {
//        return Survey.builder()
//                .title(제목)
//                .endDate(설문_종료일)
//                .member(동훈)
//                .build();
//    }
//}
