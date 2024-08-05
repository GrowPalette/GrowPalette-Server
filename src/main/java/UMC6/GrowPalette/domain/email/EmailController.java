package UMC6.GrowPalette.domain.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class EmailController {

    private final EmailService emailService;

    //인증번호 전송
    @GetMapping("/email")
    public EmailAuthResponseDto sendAuthCode(@RequestParam String address){
        return emailService.sendEmail(address);
    }

    //인증번호 검증
    @PostMapping("/email")
    public EmailAuthResponseDto checkAuthCode(@RequestParam String address, @RequestParam String authCode){
        return emailService.validateAuthCode(address, authCode);
    }


}
