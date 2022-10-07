package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerRegistrationRequest {
    private String country;
    private String email;
    private String password;
}
