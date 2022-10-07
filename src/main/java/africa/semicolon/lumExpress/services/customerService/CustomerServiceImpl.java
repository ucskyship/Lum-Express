package africa.semicolon.lumExpress.services.customerService;

import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.data.dtos.request.LoginRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumExpress.data.dtos.response.CustomerRegistrationResponse;
import africa.semicolon.lumExpress.data.dtos.response.LoginResponse;
import africa.semicolon.lumExpress.data.models.Address;
import africa.semicolon.lumExpress.data.models.Cart;
import africa.semicolon.lumExpress.data.models.Customer;
import africa.semicolon.lumExpress.data.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements iCustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) {
        var customer = modelMapper.map(registerRequest, Customer.class);
        customer.setCart(new Cart());
        Address customerAddress = new Address();
        customerAddress.setCountry(registerRequest.getCountry());
        customer.getAddress().add(customerAddress);
        var savedCustomer = customerRepository.save(customer);
        log.info("customer to be saved in db::{}", savedCustomer);

        return registrationResponseBuilder(savedCustomer);
    }

    private CustomerRegistrationResponse registrationResponseBuilder(Customer customer){
        return CustomerRegistrationResponse.builder()
                .message("successful")
                .userId(customer.getId())
                .code(201)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public String completeProfile(UpdateCustomerDetails updateCustomerDetails) {
        return null;
    }
}
