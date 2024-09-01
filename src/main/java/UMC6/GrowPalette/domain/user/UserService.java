package UMC6.GrowPalette.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(UserDtoRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .build()).getId();
    }

    public boolean authenticateUser(UserDtoRequest dto) {
        // 사용자를 이메일로 조회
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // 비밀번호 비교
            if (bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    // 회원정보 조회
    public UserDtoResponse getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> new UserDtoResponse(user.getEmail(), user.getNickname())) // 비밀번호는 제외하고 전달
                .orElse(null);
    }

    // 회원정보 수정
    public UserDtoResponse updateUser(Long userId, UserDtoRequest dto) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // 수정할 필드 업데이트
            if (dto.getEmail() != null) {
                user.setEmail(dto.getEmail());
            }
            if (dto.getPassword() != null) {
                user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            }
            if (dto.getNickname() != null) {
                user.setNickname(dto.getNickname());
            }

            User updatedUser = userRepository.save(user);
            return new UserDtoResponse(updatedUser.getEmail(), updatedUser.getNickname()); // 비밀번호는 제외하고 전달
        } else {
            return null;
        }
    }

    // 회원탈퇴
    public boolean deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    // 사용자 이름(email)으로 사용자 정보를 가져오는 메소드
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}

