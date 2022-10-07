package africa.semicolon.lumExpress.data.dtos.response;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationResponse {
    private Long userId;
    private String message;
    private int code;
}
