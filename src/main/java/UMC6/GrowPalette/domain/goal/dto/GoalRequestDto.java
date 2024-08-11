package UMC6.GrowPalette.domain.goal.dto;

import UMC6.GrowPalette.common.enums.Category;
import UMC6.GrowPalette.common.enums.GoalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GoalRequestDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateDto{
        String title;
        String content;
        GoalDate goalDate;
        String goalDetail;
        Category category;
        boolean isAchieved;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDto{
        String title;
        String content;
        GoalDate goalDate;
        String goalDetail;
        Category category;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateAchieveDto{
        boolean isAchieved;
    }

}
