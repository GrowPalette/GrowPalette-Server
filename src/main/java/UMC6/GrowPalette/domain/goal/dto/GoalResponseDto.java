package UMC6.GrowPalette.domain.goal.dto;

import UMC6.GrowPalette.common.enums.Category;
import UMC6.GrowPalette.common.enums.GoalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class GoalResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDto{
        Long goalId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GoalDto{
        Long goalId;
        String title;
        String content;
        GoalDate goalDate;
        String goalDetail;
        Category category;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GoalUpdateResultDto{
        Long goalId;
        String title;
        String content;
        GoalDate goalDate;
        String goalDetail;
        Category category;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GoalPreviewDto{
        Long goalId;
        String title;
        GoalDate goalDate;
        String goalDetail;
        Category category;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GoalPreviewListDto{
        List<GoalPreviewDto> goals;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        boolean isFirst;
        boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AchievedRateDto{
        double achievedRate;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GoalAhieveResultDto{
        Long goalId;
        boolean isAchieved;
    }
}
