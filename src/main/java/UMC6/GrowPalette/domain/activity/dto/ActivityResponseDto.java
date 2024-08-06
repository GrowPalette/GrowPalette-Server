package UMC6.GrowPalette.domain.activity.dto;

import UMC6.GrowPalette.common.enums.Category;
import UMC6.GrowPalette.common.enums.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ActivityResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDto{
        private Long activityId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityDto {
        private Long activityId;
        private String title;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String actSum;
        private String detailTitle;
        private String detailContent;
        private Category category;
        private Tag tag;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityUpdateResultDto {
        private Long activityId;
        private String title;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String actSum;
        private String detailTitle;
        private String detailContent;
        private Category category;
        private Tag tag;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityPreviewDto {
        private Long activityId;
        private String title;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String actSum;
        private String detailTitle;
        private String detailContent;
        private Category category;
        private Tag tag;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityPreviewListDto {
        List<ActivityPreviewDto> activities;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        boolean isFirst;
        boolean isLast;
    }
}
