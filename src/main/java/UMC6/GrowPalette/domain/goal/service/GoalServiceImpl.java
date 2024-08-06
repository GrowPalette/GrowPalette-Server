package UMC6.GrowPalette.domain.goal.service;

import UMC6.GrowPalette.domain.goal.Goal;
import UMC6.GrowPalette.domain.goal.GoalConverter;
import UMC6.GrowPalette.domain.goal.GoalRepository;
import UMC6.GrowPalette.domain.goal.dto.GoalRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;

    @Override
    public Goal createGoal(GoalRequestDto.CreateDto request) {
        Goal goal = GoalConverter.toGoal(request);
        return goalRepository.save(goal);
    }

    @Override
    public Goal updateGoal(Long goalId, GoalRequestDto.UpdateDto request) {
        Goal updateGoal = goalRepository.findById(goalId).get();
        updateGoal.updateGoal(request);
        return updateGoal;
    }

    @Override
    public void deleteGoal(Long goalId) {
        goalRepository.deleteById(goalId);
    }

    @Override
    public Goal getGoal(Long goalId) {
        return goalRepository.findById(goalId).get();
    }

    @Override
    public Page<Goal> findAllBySearch(Integer page, Integer size, Optional<String> optSearch) {
        PageRequest request = PageRequest.of(page, size);

        if(optSearch.isPresent()){
            String search = optSearch.get();
            return goalRepository.findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDescGoalIdDesc(search, search, request);
        }
        return goalRepository.findAllByOrderByCreatedAtDescGoalIdDesc(request);
    }
}
