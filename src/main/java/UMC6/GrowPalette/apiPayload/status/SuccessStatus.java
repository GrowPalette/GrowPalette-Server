package UMC6.GrowPalette.apiPayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus {

    // 활동 응답
    Activity_OK(HttpStatus.OK, "ACTIVITY_4000", "성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
