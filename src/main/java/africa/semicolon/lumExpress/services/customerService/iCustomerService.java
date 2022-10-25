package africa.semicolon.lumExpress.services.customerService;

import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumExpress.data.dtos.response.CustomerRegistrationResponse;
import africa.semicolon.lumExpress.data.models.Customer;
import africa.semicolon.lumExpress.exceptions.LumExpressException;
import africa.semicolon.lumExpress.exceptions.UserNotFoundException;

import java.util.List;

public interface iCustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) throws LumExpressException;
    String completeCustomerProfile(UpdateCustomerDetails updateCustomerDetails) throws UserNotFoundException;
    List<Customer> getAllCustomers();
}
