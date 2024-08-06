package UMC6.GrowPalette.domain.activity.dto;

import UMC6.GrowPalette.common.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ActivityRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateActivityDto {
        private String title;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String actSum;
        private Category category;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDetailDto{
        private String detailTitle;
        private String detailContent;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDto {
        private String title;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String actSum;
        private String detailTitle;
        private String detailContent;
        private Category category;
    }
}
