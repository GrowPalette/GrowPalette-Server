package UMC6.GrowPalette.domain.user;

import UMC6.GrowPalette.apiPayload.ApiResponse;
import UMC6.GrowPalette.apiPayload.status.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign_up")
    public ApiResponse<UserDtoResponse> join(@RequestBody @Valid UserDtoRequest request) {
        userService.save(request);
        UserDtoResponse response = new UserDtoResponse(request.getEmail(), request.getNickname());
        return ApiResponse.onSuccess(SuccessStatus.UserCreate_OK, response);
    }

    @PostMapping("/login")
    public ApiResponse<UserDtoResponse> login(HttpServletRequest httpRequest, @RequestBody @Valid UserDtoRequest userDto) {
        boolean isAuthenticated = userService.authenticateUser(userDto);
        if (isAuthenticated) {
            // Spring Security의 SecurityContext를 설정하여 인증 정보 관리
            UserDetails userDetails = userService.loadUserByUsername(userDto.getEmail());
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // SecurityContext를 세션에 반영하여 Spring Security가 관리하도록 설정
            httpRequest.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            // 로그인 성공 응답 반환
            UserDtoResponse response = new UserDtoResponse(userDto.getEmail(), userDto.getNickname());
            return ApiResponse.onSuccess(SuccessStatus.UserLogin_OK, response);
        } else {
            return ApiResponse.onFailure("LOGIN_ERROR", "이메일 또는 패스워드가 잘못되었습니다.", null);
        }
    }

    // 회원정보 조회
    @GetMapping("/{userId}")
    public ApiResponse<UserDtoResponse> getUser(@PathVariable Long userId) {
        UserDtoResponse userDto = userService.getUserById(userId);
        if (userDto != null) {
            return ApiResponse.onSuccess(SuccessStatus.UserGet_OK, userDto);
        } else {
            return ApiResponse.onFailure("USER_NOT_FOUND", "사용자를 찾을 수 없습니다.", null);
        }
    }

    // 회원정보 수정
    @PatchMapping("/{userId}")
    public ApiResponse<UserDtoResponse> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDtoRequest request) {
        UserDtoResponse updatedUserDto = userService.updateUser(userId, request);
        if (updatedUserDto != null) {
            return ApiResponse.onSuccess(SuccessStatus.UserPatch_OK, updatedUserDto);
        } else {
            return ApiResponse.onFailure("USER_NOT_FOUND", "사용자를 찾을 수 없습니다.", null);
        }
    }

    // 회원탈퇴
    @DeleteMapping("/delete/{userId}")
    public ApiResponse<Void> deleteUser(@PathVariable Long userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return ApiResponse.onSuccess(SuccessStatus.UserDelete_OK, null);
        } else {
            return ApiResponse.onFailure("USER_NOT_FOUND", "사용자를 찾을 수 없습니다.", null);
        }
    }
}