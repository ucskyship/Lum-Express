package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Validated
public class CustomerRegistrationRequest {
    @NotNull(message = "please add your country")
    @NotEmpty(message = "country cannot be empty")
    private String country;

    @NotNull(message = "please enter firstName")
    @NotEmpty(message = "firstname cannot be empty")
    private String firstName;

    @NotNull(message = "email cannot be null")
    @Email(message = "email must be valid")
    @NotEmpty(message = "email cannot be empty")
    private String email;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    private String password;
}
