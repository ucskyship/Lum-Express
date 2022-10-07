package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerDetails {
    private String firstname;
    private Long email;
    private String lastName;
    private String phoneNumber;
    private String imageUrl;
}
