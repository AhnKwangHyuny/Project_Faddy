package faddy.backend.global.api.auth;

import faddy.backend.email.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/emails")
public class EmailApiController {

    private final MailService mailService;


    @Autowired
    public EmailApiController(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 이메일 인증 코드를 검증하는 메소드.
     * @param request 이메일 주소와 인증 코드를 포함하는 EmailVerificationRequestDto 객체.
     * @return 인증 결과를 담은 EmailVerificationResult 객체를 클라이언트에 반환
     */


}
