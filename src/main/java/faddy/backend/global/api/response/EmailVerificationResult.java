package faddy.backend.global.api.response;

public class EmailVerificationResult {

    private final boolean result;

    public EmailVerificationResult(boolean result) {
        this.result = result;
    }

    // result 필드에 대한 getter 메소드
    public boolean getResult() {
        return result;
    }
}
