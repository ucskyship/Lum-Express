package africa.semicolon.lumExpress.services.customerService;

import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.data.dtos.request.EmailNotificationRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumExpress.data.dtos.response.CustomerRegistrationResponse;
import africa.semicolon.lumExpress.data.models.Address;
import africa.semicolon.lumExpress.data.models.Cart;
import africa.semicolon.lumExpress.data.models.Customer;
import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.data.repositories.CustomerRepository;
import africa.semicolon.lumExpress.services.notification.iEmailNotificationService;
import africa.semicolon.lumExpress.services.verificationTokenService.VerificationTokenServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements iCustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final VerificationTokenServiceImpl verificationTokenService;
    private final iEmailNotificationService emailNotificationService;

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) {
        var customer = modelMapper.map(registerRequest, Customer.class);
        customer.setCart(new Cart());
        setCustomerAddress(registerRequest, customer);
        var savedCustomer = customerRepository.save(customer);
        log.info("customer to be saved in db::{}", savedCustomer);
        var token = verificationTokenService.createToken(savedCustomer.getEmail());
        emailNotificationService.sendHtmlMail(buildEmailNotificationRequest(token));
        return registrationResponseBuilder(savedCustomer);
    }

    private EmailNotificationRequest buildEmailNotificationRequest(VerificationToken verificationToken) {
        var email = getEmailTemplate();
        String mail = null;
        if (email != null){
            //TODO: remove hard-code url to environment
            mail = String.format(email, verificationToken.getUserEmail(), "http://localhost:8080/api/v1/customer/verify/" + verificationToken.getToken());
        }
        return EmailNotificationRequest.builder()
                .userEmail(verificationToken.getUserEmail())
                .mailContent(mail)
                .build();
    }

    private String getEmailTemplate(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/ucj/Downloads/LumExpress-main/src/main/resources/welcome.txt"))) {
            return bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void setCustomerAddress(CustomerRegistrationRequest registerRequest, Customer customer) {
        Address customerAddress = new Address();
        customerAddress.setCountry(registerRequest.getCountry());
        customer.getAddress().add(customerAddress);
    }

    private CustomerRegistrationResponse registrationResponseBuilder(Customer customer){
        return CustomerRegistrationResponse.builder()
                .message("successful")
                .userId(customer.getId())
                .code(201)
                .build();
    }

    @Override
    public String completeProfile(UpdateCustomerDetails updateCustomerDetails) {
        return null;
    }
}
