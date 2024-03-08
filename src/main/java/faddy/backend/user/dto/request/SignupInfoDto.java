package faddy.backend.user.dto.request;

import faddy.backend.global.annotation.user.CustomEmail;
import faddy.backend.global.annotation.user.ValidNickname;
import faddy.backend.global.annotation.user.ValidPassword;
import faddy.backend.global.annotation.user.ValidUserId;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupInfoDto {

    @CustomEmail
    private String email;

    @ValidNickname
    private String nickname;

    @ValidPassword
    private String password;

    @ValidUserId
    private String username;

}
