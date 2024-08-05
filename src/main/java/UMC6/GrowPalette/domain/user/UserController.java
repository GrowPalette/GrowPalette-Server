package UMC6.GrowPalette.domain.user;

import UMC6.GrowPalette.apiPayload.ApiResponse;
import UMC6.GrowPalette.apiPayload.status.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign_up")
    public ApiResponse<UserDto> join(@RequestBody @Valid UserDto request){
        userService.save(request);
        return ApiResponse.onSuccess(SuccessStatus.Activity_OK,request);
    }

    @PostMapping("/login")
    public ApiResponse<UserDto> login(@RequestBody @Valid UserDto request) {
        boolean isAuthenticated = userService.authenticateUser(request);
        if (isAuthenticated) {
            return ApiResponse.onSuccess(SuccessStatus.Activity_OK, request);
        } else {
            return ApiResponse.onFailure("LOGIN_ERROR", "이메일 또는 패스워드가 잘못되었습니다.", null);
        }
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 로그아웃 처리
            new SecurityContextLogoutHandler().logout(request, response,
                    SecurityContextHolder.getContext().getAuthentication());

            // 로그아웃 성공 응답 반환
            return ApiResponse.onSuccess(SuccessStatus.Activity_OK, null);
        } catch (Exception e) {
            // 로그아웃 실패 시 응답 반환
            return ApiResponse.onFailure("LOGOUT_ERROR", "로그아웃에 실패하였습니다.", null);
        }
    }

    // 회원정보 조회
    @GetMapping("/{userId}")
    public ApiResponse<UserDto> getUser(@PathVariable Long userId) {
        UserDto userDto = userService.getUserById(userId);
        if (userDto != null) {
            return ApiResponse.onSuccess(SuccessStatus.Activity_OK, userDto);
        } else {
            return ApiResponse.onFailure("USER_NOT_FOUND", "사용자를 찾을 수 없습니다.", null);
        }
    }

    // 회원정보 수정
    @PatchMapping("/{userId}")
    public ApiResponse<UserDto> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDto request) {
        UserDto updatedUserDto = userService.updateUser(userId, request);
        if (updatedUserDto != null) {
            return ApiResponse.onSuccess(SuccessStatus.Activity_OK, updatedUserDto);
        } else {
            return ApiResponse.onFailure("USER_NOT_FOUND", "사용자를 찾을 수 없습니다.", null);
        }
    }
}