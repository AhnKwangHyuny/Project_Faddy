package faddy.backend.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<ExceptionResponse> handleAdminException(final MailSendException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse( HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage()));
    }

//    @ExceptionHandler(AuthCheckException.class)
//    public ResponseEntity<String> handleAuthCheckException(AuthCheckException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 번호 검증에 실패하였습니다.");
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청 파라미터입니다.");
    }


}
