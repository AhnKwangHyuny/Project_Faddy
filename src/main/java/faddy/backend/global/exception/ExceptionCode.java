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

    /**
    * @Return enttiy exception
    *
    * */
    NOT_SAVE_USER(3000 , "회원가입 처리 중 서버 오류가 발생했습니다. 잠시 후 다시 시도 부탁드립니다."),

    /**
     * @Return User info request exception : 4000~ 5000
     * */
    INVALID_INPUT_DATA(4001 , "유효하지 않은 데이터입니다."),
    DUPLICATED_INPUT_DATA(4002 , "이미 사용 중인 데이터 입니다. (중복)"),

    INVALID_USER_ID (4003 , "유효하지 않은 아이디 입니다. "),
    DUPLICATED_USER_ID(4004 , "이미 사용 중인 아이디가 존재합니다."),

    INVALID_NICKNAME (4005 , "유효하지 않은 닉네임 입니다. "),

    DUPLICATED_NICKNAME(4006 , "이미 사용 중인 닉네임이 존재합니다."),

    /*
    * DB ACCESS ERROR
    * */
    DUPLICATE_EMAIL_REDIS(2001, "이미 사용 중인 이메일 입니다."),
    DUPLICATE_EMAIL_MYSQL(2002, "이미 사용 중인 이메일 입니다"),

    /*
     * email
     * */
    INVALID_EMAIL_FORMAT(5003 , "유효하지 않은 이메일 형식입니다."),




    /* @@@ server exception : 6000~   @@@@*/

    /*
     * token & auth-code
     * */
    INVALID_TOKEN(6001, "유효하지 않은 토큰입니다."),
    INVALID_AUTH_CODE(6002, "유효하지 않은 인증 코드 입니다."),
    INVALID_EMAIL_AUTH_CODE(6003, "유효하지 않은 이메일 인증 코드 입니다."),
    INVALID_PASSWORD_AUTH_CODE(6004, "유효하지 않은 비밀번호 인증 코드 입니다."),

    /**
     *  server resource creating error
     * */
    TOKEN_GENERATION_ERROR(6010 , "인증 코드 확인에 실패했습니다. 재요청 부탁드립니다."),

    /**
     *  authentication , authorization exception error
     * */
    AUTHENTICATION_ERROR(8001 , "사용자 인증에 실패했습니다."),
    AUTHORIZATION_ERROR(8002 , "접근 권한이 없거나 만료되었습니다.");


    private final int code;
    private final String message;

    ExceptionCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }





}
