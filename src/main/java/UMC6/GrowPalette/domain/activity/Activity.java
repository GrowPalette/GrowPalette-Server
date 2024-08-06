package UMC6.GrowPalette.domain.activity;

import UMC6.GrowPalette.common.BaseEntity;
import UMC6.GrowPalette.common.enums.Category;
import UMC6.GrowPalette.common.enums.Tag;
import UMC6.GrowPalette.domain.activity.dto.ActivityRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Activity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @Column(nullable = false, length = 50)
    private String title;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(length = 225)
    private String actSum;

    @Column(length = 50)
    private String detailTitle;

    @Column(length = 225)
    private String detailContent;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'CLUB'")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'IT'")
    private Tag tag;

    public void createDetailActivity(ActivityRequestDto.CreateDetailDto activity) {
        this.detailTitle = activity.getDetailTitle();
        this.detailContent = activity.getDetailContent();
    }

    public void updateActivity(ActivityRequestDto.UpdateDto activity) {
        this.title = activity.getTitle();
        this.detailTitle = activity.getDetailTitle();
        this.detailContent = activity.getDetailContent();
    }

}
