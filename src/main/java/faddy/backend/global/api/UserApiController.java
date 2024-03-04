package faddy.backend.global.api;

import faddy.backend.email.dto.EmailDto;
import faddy.backend.email.service.MailService;
import faddy.backend.global.api.Dto.ResponseDto;
import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.ExceptionResponse;
import faddy.backend.user.repository.UserRepository;
import faddy.backend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(final BadRequestException e) {
        // 이 메서드에서는 로그 메시지를 출력하지 않습니다.

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(e.getCode() , e.getMessage() ));
    }


    /**
     * 사용자 이름의 중복 여부를 확인하는 메소드.
     * @param user 사용자 이름을 포함하는 User 객체.
     * @return 중복 시 true , 사용 가능할 경우 false를 클라이언트에 반환
     */
    @PostMapping("/check-duplication/userId")
    public ResponseEntity<ResponseDto> checkUserIdDuplication(@RequestBody Map<String , String> user) {

        log.info(user.toString());

        Map<String , Object> response = new HashMap<>();
        String userId = user.get("userId");

        // 사용자 아이다가 없거나 null 인경우 http code 400 반환 (request error)
        if(userId == null || userId.isEmpty()) {

            throw new BadRequestException(ExceptionCode.INVALID_USER_ID);
        }

        if(userService.isUserIdDuplicated(userId)) {
            throw new BadRequestException(ExceptionCode.DUPLICATED_USER_ID);
        }


        return ResponseEntity.ok().body(
                ResponseDto.response(
                        "200",
                        "사용 가능한 아이디 입니다."
                )
        );

    }

    // 이메일 중복 확인 요청
    @PostMapping("/email/duplicates")
    public ResponseEntity<?> checkDuplication(@RequestBody @Valid EmailDto emailDto) {

        String email = emailDto.getEmail();
        Map<String , Object>  response = new HashMap<>();

        // 이메일 중복 확인

        mailService.checkDuplication(email);



        response.put("message" , "사용 가능한 이메일 입니다.");
        response.put("isDuplicated", false);

        return ResponseEntity.ok().body(response);
    }


}
