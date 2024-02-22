package faddy.backend.user.dto.request;

import faddy.backend.annotation.CustomEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailVerificationRequestDto {

    @CustomEmail
    @Valid
    private String email;

    @NotNull
    @Valid
    private String code;

}
