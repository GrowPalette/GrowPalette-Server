package UMC6.GrowPalette.domain.activity;

import UMC6.GrowPalette.apiPayload.ApiResponse;
import UMC6.GrowPalette.apiPayload.status.SuccessStatus;
import UMC6.GrowPalette.domain.activity.dto.ActivityRequestDto;
import UMC6.GrowPalette.domain.activity.dto.ActivityResponseDto;
import UMC6.GrowPalette.domain.activity.service.ActivityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityServiceImpl activityService;

    // 활동 생성
    @PostMapping
    @Operation(summary = "Create Activity API" , description = "Request Body에 생성할 활동을 입력하세요.")
    public ApiResponse<ActivityResponseDto.CreateResultDto> createActivity(@RequestBody @Valid ActivityRequestDto.CreateActivityDto request) {
        return ApiResponse.onSuccess(
                SuccessStatus.ActivityCreate_OK,
                ActivityConverter.toCreateResultDto(activityService.createActivity(request))
        );

    }

    // 세부 기록 생성
    @PostMapping("/{activityId}/detail")
    @Operation(summary = "Create Detail Activity API" , description = "Path variable에 Activity ID를 입력하세요. Request Body에 생성할 세부 기록을 입력하세요.")
    public ApiResponse<ActivityResponseDto.CreateResultDto> createActivity(
            @PathVariable("activityId") Long activityId
            , @RequestBody @Valid ActivityRequestDto.CreateDetailDto request) {

        return ApiResponse.onSuccess(
                SuccessStatus.ActivityDetailCreate_OK,
                ActivityConverter.toCreateResultDto(activityService.createDetailActivity(activityId, request))
        );

    }

    // 활동 수정
    @PatchMapping("/{activityId}")
    @Operation(summary = "Update Activity API", description = "path variable로 수정 할 activityId를 입력하세요(세부 기록 포함).")
    public ApiResponse<ActivityResponseDto.ActivityUpdateResultDto> updateActivity(
            @RequestBody @Valid ActivityRequestDto.UpdateDto request
            , @PathVariable("activityId") Long activityId) {
        return ApiResponse.onSuccess(
                SuccessStatus.ActivityUpdate_OK,
                ActivityConverter.toUpdateResultDto(activityService.updateActivity(activityId, request))
        );
    }

    // 활동 삭제
    @DeleteMapping("/{activityId}")
    @Operation(summary = "Delete Activity API", description = "path variable로 삭제 할 activityId를 입력하세요.")
    public ApiResponse<?> deleteActivity(@PathVariable("activityId") Long activityId) { //@RequestParam Long userId
        activityService.deleteActivity(activityId);
        return ApiResponse.onSuccess(SuccessStatus.ActivityDelete_OK, null);
    }


    // 특정 활동 조회
    @GetMapping("/{activityId}")
    @Operation(summary = "Get Activity API", description = "path variable로 조회 할 activityId를 입력하세요.")
    public ApiResponse<ActivityResponseDto.ActivityDto> getActivity( @PathVariable("activityId") Long activityId) {
        Activity getActivity = activityService.getActivity(activityId);
        return ApiResponse.onSuccess(
                SuccessStatus.ActivityGet_OK,
                ActivityConverter.toActivityGet(getActivity)
        );
    }

    // 전체 활동 조회
    @GetMapping
    @Operation(
            summary = "Get All Activities API", description = "RequestParam으로 페이징 조회를 위한 page와 size를 입력하세요. 검색을 원할 경우 search 를 입력하세요"
    )
    public ApiResponse<ActivityResponseDto.ActivityPreviewListDto> getAllActivitiesByPaging(
            @RequestParam("page") @Min(0) Integer page
            , @RequestParam("size") @Min(1) @Max(10) Integer size
            , @RequestParam(name = "search", required = false) Optional<String> search) {

        Page<Activity> activities = activityService.getAllActivitiesBySearch(page, size, search);
        return ApiResponse.onSuccess(
                SuccessStatus.ActivityGetAll_OK,
                ActivityConverter.toActivityPreviewList(activities)
        );

    }
}
