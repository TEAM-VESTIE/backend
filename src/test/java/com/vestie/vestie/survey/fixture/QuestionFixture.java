package com.vestie.vestie.survey.fixture;

import com.vestie.vestie.survey.domain.MultipleChoiceQuestion;
import com.vestie.vestie.survey.domain.Option;
import com.vestie.vestie.survey.domain.Question;
import com.vestie.vestie.survey.domain.SubjectiveQuestion;

import java.util.List;

import static com.vestie.vestie.survey.domain.MultipleChoiceQuestion.ChoiceConstraints.SINGLE;

public class QuestionFixture {

    public static List<Question> 질문들() {
        return List.of(객관식질문(1), 주관식질문(2), 주관식질문(3), 객관식질문(4));
    }

    public static MultipleChoiceQuestion 객관식질문(int num) {
        return new MultipleChoiceQuestion("베스티를 사랑하는 이유를 골라주세요"+num, SINGLE, 선택지());
    }

    public static List<Option> 선택지() {
        return List.of(new Option("이름이 멋있어서"),
                        new Option("디자인이 예뻐서"), new Option("필요한 앱이라서"));
    }

    public static SubjectiveQuestion 주관식질문(int num) {
        return new SubjectiveQuestion("베스티를 사랑하시나요?"+num);
    }
}
