package faddy.backend.global.api;

import faddy.backend.email.service.MailService;
import faddy.backend.global.api.Dto.SingleResponseDto;
import faddy.backend.global.api.response.EmailVerificationResult;
import faddy.backend.user.dto.request.EmailRequestDto;
import faddy.backend.user.dto.request.EmailVerificationRequestDto;
import faddy.backend.user.repository.UserRepository;
import faddy.backend.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final MailService mailService;

    @Autowired
    public UserApiController(UserRepository userRepository, UserService userService, MailService mailService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
    }

    /**
     * 사용자 이름의 중복 여부를 확인하는 메소드.
     * @param user 사용자 이름을 포함하는 User 객체.
     * @return 중복 시 true , 사용 가능할 경우 false를 클라이언트에 반환
     */
    @PostMapping("/check-duplication")
    public ResponseEntity<?> checkUserIdDuplication(@RequestBody Map<String , String> user) {

        log.info(user.toString());

        Map<String , Object> response = new HashMap<>();
        String userId = user.get("userId");

        // 사용자 아이다가 없거나 null 인경우 http code 400 반환 (request error)
        if(userId == null || userId.isEmpty()) {
            response.put("message" , "유저 아이디는 필수 입니다. (아이디 입력 안됨)");
            response.put("isDuplicated" , true);

            return ResponseEntity.badRequest().body(response);
        }

        if(userService.isUserIdDuplicated(userId)) {
            response.put("message" , "중복된 아이디가 존재합니다.");
            response.put("isDuplicated" , true);

            return ResponseEntity.ok().body(response);
        }

        response.put("message" , "사용 가능한 아이디입니다.");
        response.put("isDuplicated" , false);

        return ResponseEntity.ok().body(response);

    }




}
