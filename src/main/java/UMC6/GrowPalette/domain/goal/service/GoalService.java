package UMC6.GrowPalette.domain.goal.service;

import UMC6.GrowPalette.domain.goal.Goal;
import UMC6.GrowPalette.domain.goal.dto.GoalRequestDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface GoalService {
    Goal createGoal(GoalRequestDto.CreateDto request);

    Goal updateGoal(Long goalId, GoalRequestDto.UpdateDto request);

    void deleteGoal(Long goalId);

    Goal getGoal(Long goalId);

    Page<Goal> findAllBySearch(Integer page, Integer size, Optional<String> optSearch);

    double calculateAchievementRate();

    void achieveGoal(Long goalId);

}
