package UMC6.GrowPalette.domain.activity;

import UMC6.GrowPalette.domain.activity.dto.ActivityRequestDto;
import UMC6.GrowPalette.domain.activity.dto.ActivityResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityConverter {
    public static Activity toActivityCreate(ActivityRequestDto.CreateActivityDto activity) {
        return Activity.builder()
                .title(activity.getTitle())
                .startDate(activity.getStartDate())
                .endDate(activity.getEndDate())
                .build();
    }

    public static ActivityResponseDto.ActivityDto toActivityGet(Activity activity) {
        return ActivityResponseDto.ActivityDto.builder()
                .activityId(activity.getActivityId())
                .title(activity.getTitle())
                .startDate(activity.getStartDate())
                .endDate(activity.getEndDate())
                .detailTitle(activity.getDetailTitle())
                .detailContent(activity.getDetailContent())
                .createdAt(activity.getCreatedAt())
                .updatedAt(activity.getUpdatedAt())
                .build();
    }

    public static ActivityResponseDto.CreateResultDto toCreateResultDto(Activity activity) {
        return ActivityResponseDto.CreateResultDto.builder()
                .activityId(activity.getActivityId())
                .createdAt(activity.getCreatedAt())
                .build();
    }

    public static ActivityResponseDto.ActivityUpdateResultDto toUpdateResultDto(Activity activity) {
        return ActivityResponseDto.ActivityUpdateResultDto.builder()
                .activityId(activity.getActivityId())
                .updatedAt(activity.getUpdatedAt())
                .build();
    }

    public static ActivityResponseDto.ActivityPreviewDto toActivityPreviewDto(Activity activity) {
        return ActivityResponseDto.ActivityPreviewDto.builder()
                .activityId(activity.getActivityId())
                .title(activity.getTitle())
                .startDate(activity.getStartDate())
                .endDate(activity.getEndDate())
                .detailTitle(activity.getDetailTitle())
                .detailContent(activity.getDetailContent())
                .build();
    }
//
//    public static ActivityResponseDto.ActivityPreviewListDto toActivityPreviewList(Page<Activity> activities) {
//        List<ActivityResponseDto.ActivityPreviewDto> activityPreviewDtoList = activities.getContent().stream()
//                .map(activity -> toActivityPreviewDto(activity).builder()
//                        .activityId(activity.getActivityId())
//                        .title(activity.getTitle())
//                        .startDate(activity.getStartDate())
//                        .endDate(activity.getEndDate())
//                        .detailTitle(activity.getDetailTitle())
//                        .detailContent(activity.getDetailContent())
//                        .build())
//                .collect(Collectors.toList());
//
//        return ActivityResponseDto.ActivityPreviewListDto.builder()
//                .activities(activityPreviewDtoList)
//                .listSize(activities.getSize())
//                .totalPage(activities.getTotalPages())
//                .totalElements(activities.getTotalElements())
//                .isFirst(activities.isFirst())
//                .isLast(activities.isLast())
//                .build();
//    }

    public static ActivityResponseDto.ActivityPreviewListDto toActivityPreviewList(Page<Activity> activities) {
        List<ActivityResponseDto.ActivityPreviewDto> activityPreviewDtoList = activities.getContent().stream()
                .map(activity -> {
                    ActivityResponseDto.ActivityPreviewDto dto = toActivityPreviewDto(activity);
                    return dto.builder() // toBuilder() 메서드를 사용한다고 가정
                            .activityId(activity.getActivityId())
                            .title(activity.getTitle())
                            .startDate(activity.getStartDate())
                            .endDate(activity.getEndDate())
                            .detailTitle(activity.getDetailTitle())
                            .detailContent(activity.getDetailContent())
                            .build();
                })
                .collect(Collectors.toList());

        return ActivityResponseDto.ActivityPreviewListDto.builder()
                .activities(activityPreviewDtoList)
                .listSize(activities.getContent().size()) // 전체 리스트의 크기로 변경
                .totalPage(activities.getTotalPages())
                .totalElements(activities.getTotalElements())
                .isFirst(activities.isFirst())
                .isLast(activities.isLast())
                .build();
    }

}
