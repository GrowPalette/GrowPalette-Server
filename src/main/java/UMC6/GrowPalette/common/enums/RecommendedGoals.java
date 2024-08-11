package UMC6.GrowPalette.common.enums;

import lombok.Getter;

@Getter
public enum RecommendedGoals {
    LANGUAGE("어학", new String[] {
            "영어회화 공부하기"
            , "토익 스터디"
            , "영어책 읽기"
            , "팝송 듣기"
    }),

    SELF_DEVELOPMENT("자기계발", new String[] {
            "외국어 배우기"
            , "코딩 배우기"
            , "자격증 공부하기"
            , "온라인 강의 듣기"
            , "전자책 읽기"
    }),

    LIFE("라이프", new String[] {
            "조깅하기"
            , "홈트레이닝"
            , "필라테스"
            , "방청소 하기"
            , "산책하기"
    }),

    CAREER("커리어", new String[] {
            "대외 활동"
            , "공모전 참가하기"
            , "해커톤 신청하기"
            , "포트폴리오 만들기"
    });

    private final String recommendCategory;
    private final String[] recommendGoals;

    RecommendedGoals(String recommendCategory, String[] recommendGoals) {
        this.recommendCategory = recommendCategory;
        this.recommendGoals = recommendGoals;
    }


}
