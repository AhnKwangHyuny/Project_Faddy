package faddy.backend.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum ExceptionCode {

    INVALID_REQUEST(1000, "올바르지 않은 요청입니다."),
    NOT_FOUND_USER_ID(1001, ""),
    NOT_FOUND_DAY_LOG_ID(1002, "요청한 ID에 해당하는 데이로그가 존재하지 않습니다."),
    NOT_FOUND_TRIP_ITEM_ID(1003, "요청한 ID에 해당하는 여행 아이템이 존재하지 않습니다."),
    NOT_FOUND_EXPENSE_ID(1004, "요청한 ID에 해당하는 금액이 존재하지 않습니다."),

    /*
    * DB ACCESS ERROR
    * */
    DUPLICATE_EMAIL_REDIS(2001, "이미 사용 중인 이메일 입니다."),
    DUPLICATE_EMAIL_MYSQL(2002, "이미 사용 중인 이메일 입니다"),

    /*
     * email
     * */
    INVALID_EMAIL_FORMAT(5003 , "유효하지 않은 이메일 형식입니다.");



    private final int code;
    private final String message;

    ExceptionCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }





}
