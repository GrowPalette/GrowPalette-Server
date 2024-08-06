package UMC6.GrowPalette.domain.goal;

import UMC6.GrowPalette.apiPayload.ApiResponse;
import UMC6.GrowPalette.apiPayload.status.SuccessStatus;
import UMC6.GrowPalette.domain.goal.dto.GoalRequestDto;
import UMC6.GrowPalette.domain.goal.dto.GoalResponseDto;
import UMC6.GrowPalette.domain.goal.service.GoalService;
import com.sun.net.httpserver.Authenticator;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;

    @PostMapping
    @Operation(summary = "목표 생성 API"
            , description = "Request body에 생성할 목표를 입력하세요.")
    public ApiResponse<GoalResponseDto.CreateResultDto> createGoal(@RequestBody GoalRequestDto.CreateDto request) {
        return ApiResponse.onSuccess(SuccessStatus.Goal_OK,
                GoalConverter.toCreateResultDto(goalService.createGoal(request)));
    }

    @GetMapping("/{goalId}")
    @Operation(summary = "목표 조회 API"
            , description = "path variable로 goalId를 입력하세요.")
    public ApiResponse<GoalResponseDto.GoalDto> getGoal(@PathVariable("goalId") Long goalId) {

        return ApiResponse.onSuccess(SuccessStatus.Goal_OK,
                GoalConverter.toGoalDto(goalService.getGoal(goalId)));

    }

    @GetMapping
    @Operation(summary = "목표 전체 조회 API"
            , description = "page, size, search를 query parameter로 입력하세요.")
    public ApiResponse<GoalResponseDto.GoalPreviewListDto> getAllGoalsByPaging(
            @RequestParam(name = "page") @Min(0) Integer page
            , @RequestParam(name = "size") @Min(1) @Max(10) Integer size
            , @RequestParam(name = "search", required = false) Optional<String> search) {

        Page<Goal> goals = goalService.findAllBySearch(page, size, search);
        return ApiResponse.onSuccess(SuccessStatus.Goal_OK,
                GoalConverter.toGoalPreviewListDto(goals));
    }

    @PatchMapping("/{goalId}")
    @Operation(summary = "목표 수정 API"
            , description = "path variable로 goalId를 입력하고, Request body에 수정할 목표를 입력하세요.")
    public ApiResponse<GoalResponseDto.GoalUpdateResultDto> updateGoal(@PathVariable("goalId") Long goalId, @RequestBody GoalRequestDto.UpdateDto request) {
        return ApiResponse.onSuccess(SuccessStatus.Goal_OK,
                GoalConverter.toGoalUpdateResultDto(goalService.updateGoal(goalId, request)));
    }

    @DeleteMapping("/{goalId}")
    @Operation(summary = "목표 삭제 API"
            , description = "path variable로 goalId를 입력하세요.")
    public ApiResponse<?> deleteGoal(@PathVariable("goalId") Long goalId) {
        goalService.deleteGoal(goalId);
        return ApiResponse.onSuccess(SuccessStatus.Goal_OK, null);

    }

}
