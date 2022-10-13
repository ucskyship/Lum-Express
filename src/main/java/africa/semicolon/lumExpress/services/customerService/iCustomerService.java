package africa.semicolon.lumExpress.services.customerService;

import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumExpress.data.dtos.response.CustomerRegistrationResponse;

public interface iCustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest);
    String updateProfile(UpdateCustomerDetails updateCustomerDetails);
}
