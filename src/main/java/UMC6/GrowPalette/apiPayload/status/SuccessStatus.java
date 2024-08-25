package UMC6.GrowPalette.apiPayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus {

    // 목표 응답
    GoalCreate_OK(HttpStatus.OK, "GOAL_3000", "목표 생성 성공입니다."),
    GoalGet_OK(HttpStatus.OK, "GOAL_3001", "목표 조회 성공입니다."),
    GoalGetAll_OK(HttpStatus.OK, "GOAL_3002", "모든 목표 조회 성공입니다."),
    GoalUpdate_OK(HttpStatus.OK, "GOAL_3003", "목표 수정 성공입니다."),
    GoalDelete_OK(HttpStatus.OK, "GOAL_3004", "목표 삭제 성공입니다."),
    GoalAchievedRate_OK(HttpStatus.OK, "GOAL_3005", "목표 성공률 조회 성공입니다."),
    GoalAchieve_OK(HttpStatus.OK, "GOAL_3006", "목표 달성 성공입니다."),

    // 활동 응답
    Activity_OK(HttpStatus.OK, "ACTIVITY_4000", "성공입니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
