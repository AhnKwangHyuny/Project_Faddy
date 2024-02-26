package faddy.backend.email.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmailDto {
    @Email
    private String email;

    public EmailDto() {
    }

    public EmailDto(String email) {
        this.email = email;
    }
}
