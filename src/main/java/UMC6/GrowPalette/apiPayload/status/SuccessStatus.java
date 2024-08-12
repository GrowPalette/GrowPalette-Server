package UMC6.GrowPalette.apiPayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus {

    //유저 응답
    UserCreate_OK(HttpStatus.OK, "USER_1000", "회원가입 성공입니다."),
    UserLogin_OK(HttpStatus.OK, "USER_1001", "로그인 성공입니다."),
    UserLogout_OK(HttpStatus.OK, "USER_1002", "로그아웃 성공입니다."),
    UserGet_OK(HttpStatus.OK, "USER_1003", "회원정보 조회 성공입니다."),
    UserPatch_OK(HttpStatus.OK, "USER_1004", "회원정보 수정 성공입니다."),
    UserDelete_OK(HttpStatus.OK, "USER_1005", "회원탈퇴 성공입니다."),

    //프로필 응답
    ProfileCreate_OK(HttpStatus.OK, "USER_1006", "프로필 생성 성공입니다."),
    ProfileGet_OK(HttpStatus.OK, "USER_1007", "프로필 조회 성공입니다."),
    ProfilePatch_OK(HttpStatus.OK, "USER_1008", "프로필 수정 성공입니다."),

    //약관동의 응답
    Terms_OK(HttpStatus.OK, "USER_2000", "약관동의 성공입니다."),
    TermsGet_OK(HttpStatus.OK, "USER_2001", "약관조회 성공입니다."),

    // 활동 응답
    Activity_OK(HttpStatus.OK, "ACTIVITY_4000", "성공입니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
