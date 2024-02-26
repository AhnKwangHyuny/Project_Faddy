package faddy.backend.global.api.auth;

import faddy.backend.email.dto.EmailDto;
import faddy.backend.email.service.MailService;
import faddy.backend.global.api.Dto.SingleResponseDto;
import faddy.backend.global.api.response.EmailVerificationResult;
import faddy.backend.user.dto.request.EmailRequestDto;
import faddy.backend.user.dto.request.EmailVerificationRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
    @PostMapping("/verifications")
    public ResponseEntity verificationEmail(@RequestBody EmailVerificationRequestDto request , HttpServletResponse response) throws NoSuchAlgorithmException {

        String email = request.getEmail();
        String code = request.getCode();

        EmailVerificationResult result = mailService.verifiedCode(email, code);

        if(result.getResult()) {
            // 인증 성공 시 쿠기 생성
            // HMAC-SHA512 암호화



            //responseEntity에 쿠키 저장 후 클라이언트에 전송
        }

        return new ResponseEntity<>(new SingleResponseDto<>(result), HttpStatus.OK);
    }

    /**
     * 이메일 인증 코드를 발송하는 메소드.
     * @param emailDto 이메일 주소를 포함하는 EmailRequestDto 객체.
     * @return 인증 코드 발송 결과를 HTTP 상태 코드로 클라이언트에 반환
     */

    @PostMapping("/verification-requests")
    public ResponseEntity sendMessage(@RequestBody EmailRequestDto emailDto) throws NoSuchAlgorithmException {

        String email = emailDto.getEmail();

        if(email.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        mailService.sendCodeToMail(email); // 이메일 유효성 , 중복 검사 후 인증코드 발송
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
