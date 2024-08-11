package UMC6.GrowPalette.apiPayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus {

    // 활동 응답
    ActivityCreate_OK(HttpStatus.OK, "ACTIVITY_4000", "활동 생성 성공입니다."),
    ActivityDetailCreate_OK(HttpStatus.OK, "ACTIVITY_4001", "세부 기록 생성 성공입니다."),
    ActivityGet_OK(HttpStatus.OK, "ACTIVITY_4002", "활동 조회 성공입니다."),
    ActivityGetAll_OK(HttpStatus.OK, "ACTIVITY_4003", "모든 활동 조회 성공입니다."),
    ActivityUpdate_OK(HttpStatus.OK, "ACTIVITY_4004", "활동 수정 성공입니다."),
    ActivityDelete_OK(HttpStatus.OK, "ACTIVITY_4005", "활동 삭제 성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
