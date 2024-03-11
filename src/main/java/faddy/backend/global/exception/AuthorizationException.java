package faddy.backend.global.exception;

import lombok.Getter;

@Getter
public class AuthorizationException extends RuntimeException {

    private final int code;
    private final String message;

    public AuthorizationException(final ExceptionCode exceptionCode) {

        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();

    }

}