package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerDetails {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String imageUrl;
    private int buildingNumber;
    private String city;
    private String state;
    private String street;
}
