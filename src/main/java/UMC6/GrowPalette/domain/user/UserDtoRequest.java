package UMC6.GrowPalette.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoRequest {
    @Email(message = "유효한 이메일 주소를 입력하세요.")
    @NotEmpty(message = "이메일은 필수 항목입니다.")
    private String email;

    @NotEmpty(message = "패스워드는 필수 항목입니다.")
    private String password;

    @NotEmpty(message = "닉네임은 필수 항목입니다.")
    private String nickname;
}
