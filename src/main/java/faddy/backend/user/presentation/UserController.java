package faddy.backend.user.presentation;

import faddy.backend.auth.dto.LoginRequestDto;
import faddy.backend.email.dto.EmailDto;
import faddy.backend.email.service.MailService;
import faddy.backend.api.Dto.ResponseDto;
import faddy.backend.global.Utils.UserValidator;
import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.ExceptionResponse;
import faddy.backend.user.dto.request.SignupInfoDto;
import faddy.backend.user.repository.UserRepository;
import faddy.backend.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final MailService mailService;
    @Autowired
    public UserController(UserRepository userRepository, UserService userService, MailService mailService) {
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
    @ApiOperation(value = "이메일 중복 확인 " , notes = "회원가입 시 입력된 유저 정보에 중복 여부 확인 ")

    @PostMapping("/email/duplicate")
    public ResponseEntity<?> checkDuplication(@RequestBody @Valid EmailDto emailDto) {

        String email = emailDto.getEmail();
        Map<String , Object> response = new HashMap<>();

        // 이메일 중복 확인
        mailService.checkDuplication(email);

        response.put("message" , "사용 가능한 이메일 입니다.");
        response.put("isDuplicated", false);


        return ResponseEntity.ok().body(response);
    }


    @ApiOperation(value = "유저 회원가입", notes = "유저 회원가입 정보 유효성 검사 진행 후 db에 저장 성공 시 클라이언트 확인 용으로 유저 username 리턴")
    @PostMapping
    public ResponseEntity<ResponseDto> join(@RequestBody SignupInfoDto joinInfo) {

        // joinInfo 검증

        Optional<String> username = userService.joinUser(joinInfo);

        Boolean isValid = username
                .filter(value -> value.equals(joinInfo.getUsername()))
                .isPresent();

        if(Boolean.TRUE.equals(isValid)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ResponseDto.response(
                            "201",
                            "환영합니다! 회원가입이 성공적으로 진행되었습니다.",
                            Collections.singletonMap("username", username.get())
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseDto.response(
                        "500",
                        "죄송합니다. 예상치 못한 오류로 회원가입에 실패했습니다. 다시 가입 부탁드립니다."
                )
        );
    }


    @ApiOperation(value = "유저 정보 중복확인 " , notes = "회원가입 시 입력된 유저 정보에 중복 여부 확인 ")
    @PostMapping("/check-duplication/{field}")
    public ResponseEntity<ResponseDto> checkDuplication(@PathVariable("field") @Valid String field, @RequestBody Map<String , String> user) {

        String value = user.get(field);

        // 사용자 아이다가 없거나 null 인경우 http code 400 반환 (request error)
        if(value == null || value.isEmpty()) {

            throw new BadRequestException(ExceptionCode.INVALID_INPUT_DATA);
        }

        if(userService.isUserIdDuplicated(field , value)) {
            throw new BadRequestException(ExceptionCode.DUPLICATED_INPUT_DATA);
        }


        return ResponseEntity.ok().body(
                ResponseDto.response(
                        "200",
                        "사용 가능한 " + field + " 입니다."
                )
        );

    }

    @ApiOperation(value = "유저 로그인 유효성 검사" , notes = "로그인 요청 시, 아이디와 패스워드를 확인하고 토큰을 생성하여 반환")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginRequestDto loginInfo) {

        String username = loginInfo.getUsername();
        String password = loginInfo.getPassword();

        // username password 값 유효성 검사
        if(!UserValidator.isValidUsername(username) || !UserValidator.isValidPassword(password)) {

            // 유효성 검사 실패 시 bad Request 요청
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseDto.response(
                            "400",
                            "잘못된 로그인 정보입니다. 다시 입력바랍니다."
                    )
            );
        }

        return null;
    }


}
