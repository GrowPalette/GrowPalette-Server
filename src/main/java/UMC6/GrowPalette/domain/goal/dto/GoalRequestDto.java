package UMC6.GrowPalette.domain.goal.dto;

import UMC6.GrowPalette.common.enums.Category;
import UMC6.GrowPalette.common.enums.GoalDate;
import UMC6.GrowPalette.common.enums.GoalStatus;
import UMC6.GrowPalette.domain.goal.Goal;
import jakarta.validation.constraints.NotNull;
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
        GoalStatus goalStatus;
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

}
