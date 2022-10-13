package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerDetails {
    private Long customerId;
    private String lastName;
    private String phoneNumber;
    private String imageUrl;
}
