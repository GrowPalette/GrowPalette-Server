package UMC6.GrowPalette.domain.goal;

import UMC6.GrowPalette.domain.goal.dto.GoalRequestDto;
import UMC6.GrowPalette.domain.goal.dto.GoalResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public class GoalConverter {
    public static Goal toGoal(GoalRequestDto.CreateDto request) {
        return Goal.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .goalDate(request.getGoalDate())
                .goalDetail(request.getGoalDetail())
                .category(request.getCategory())
                .build();
    }

    public static GoalResponseDto.CreateResultDto toCreateResultDto(Goal goal) {
        return GoalResponseDto.CreateResultDto.builder()
                .goalId(goal.getGoalId())
                .createdAt(goal.getCreatedAt())
                .build();
    }

    public static GoalResponseDto.GoalDto toGoalDto(Goal goal) {
        return GoalResponseDto.GoalDto.builder()
                .goalId(goal.getGoalId())
                .title(goal.getTitle())
                .content(goal.getContent())
                .goalDate(goal.getGoalDate())
                .goalDetail(goal.getGoalDetail())
                .category(goal.getCategory())
                .createdAt(goal.getCreatedAt())
                .updatedAt(goal.getUpdatedAt())
                .build();
    }

    public static GoalResponseDto.GoalPreviewDto toGoalPreviewDto(Goal goal) {
        return GoalResponseDto.GoalPreviewDto.builder()
                .goalId(goal.getGoalId())
                .title(goal.getTitle())
                .goalDate(goal.getGoalDate())
                .goalDetail(goal.getGoalDetail())
                .category(goal.getCategory())
                .createdAt(goal.getCreatedAt())
                .updatedAt(goal.getUpdatedAt())
                .build();
    }

    public static GoalResponseDto.GoalPreviewListDto toGoalPreviewListDto(Page<Goal> goals) {
        List<GoalResponseDto.GoalPreviewDto> goalPreviewDtoList = goals.getContent().stream()
                .map(GoalConverter::toGoalPreviewDto)
                .toList();

        return GoalResponseDto.GoalPreviewListDto.builder()
                .goals(goalPreviewDtoList)
                .listSize(goals.getSize())
                .totalPage(goals.getTotalPages())
                .totalElements(goals.getTotalElements())
                .isFirst(goals.isFirst())
                .isLast(goals.isLast())
                .build();

    }

    public static GoalResponseDto.GoalUpdateResultDto toGoalUpdateResultDto(Goal goal) {
        return GoalResponseDto.GoalUpdateResultDto.builder()
                .goalId(goal.getGoalId())
                .title(goal.getTitle())
                .content(goal.getContent())
                .goalDate(goal.getGoalDate())
                .goalDetail(goal.getGoalDetail())
                .category(goal.getCategory())
                .updatedAt(goal.getUpdatedAt())
                .build();
    }

}
