package faddy.backend.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(final BadRequestException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(e.getCode() , e.getMessage() ));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(final NullPointerException e) {
        log.warn(e.getMessage() , e);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
        log.warn(ex.getMessage() , ex);

        // 클라이언트에게 오류 메시지를 반환합니다.
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value() , ex.getMessage()));
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
//        log.warn(e.getMessage() , e);
//
//        return ResponseEntity.badRequest()
//                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
//
//    }
    // server error
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ExceptionResponse> handleInternalServerException(final InternalServerException e) {
        log.warn(e.getMessage() , e);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));

    }
}

