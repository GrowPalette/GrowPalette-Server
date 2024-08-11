package UMC6.GrowPalette.domain.goal;

import UMC6.GrowPalette.common.BaseEntity;
import UMC6.GrowPalette.common.enums.Category;
import UMC6.GrowPalette.common.enums.GoalDate;
import UMC6.GrowPalette.domain.goal.dto.GoalRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Goal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

    @Column(length = 50)
    private String title;

    @Column(length = 50)
    private String content;

    @Enumerated(EnumType.STRING)
    private GoalDate goalDate;

    @Column(length = 10)
    private String goalDetail;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(columnDefinition = "boolean default false")
    private boolean isAchieved = false;

    public void updateGoal(GoalRequestDto.UpdateDto request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.goalDate = request.getGoalDate();
        this.goalDetail = request.getGoalDetail();
        this.category = request.getCategory();
    }

    public void updateAchieve() {
        this.isAchieved = true;
    }
}
