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
import africa.semicolon.lumExpress.exceptions.LumExpressException;
import africa.semicolon.lumExpress.exceptions.UserNotFoundException;
import africa.semicolon.lumExpress.services.notification.iEmailNotificationService;
import africa.semicolon.lumExpress.services.verificationTokenService.VerificationTokenServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements iCustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final VerificationTokenServiceImpl verificationTokenService;
    private final iEmailNotificationService emailNotificationService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) throws LumExpressException {
        var foundCustomer = customerRepository.findByEmail(registerRequest.getEmail());
        if (foundCustomer.isPresent()) throw new LumExpressException(String.format("user with email %s, already in use", registerRequest.getEmail()));
        var customer = modelMapper.map(registerRequest, Customer.class);
        customer.setCart(new Cart());
        setCustomerAddress(registerRequest, customer);
        var encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        var savedCustomer = customerRepository.save(customer);
        log.info("customer to be saved in db::{}", savedCustomer);
        var token = verificationTokenService.createToken(savedCustomer.getEmail());
        emailNotificationService.sendHtmlMail(buildEmailNotificationRequest(token, savedCustomer.getFirstname()));
        return registrationResponseBuilder(savedCustomer);
    }

    private EmailNotificationRequest buildEmailNotificationRequest(VerificationToken verificationToken, String customerName) {
        var email = getEmailTemplate();
        String mail = null;
        if (email != null){
            //TODO: remove hard-code url to environment
            var verificationUrl = "http://localhost:8080/api/v1/customer/verify/" + verificationToken.getToken();
            mail = String.format(email, customerName,  verificationUrl);
            log.info("mailed url::{}", verificationUrl);
        }
        return EmailNotificationRequest.builder()
                .userEmail(verificationToken.getUserEmail())
                .mailContent(mail)
                .build();
    }

    private String getEmailTemplate(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                "/home/ucj/Documents/SEMICOLON/IdeaProjects/LumExpress/src/main/resources/welcome.txt"))) {
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
    public String completeCustomerProfile(UpdateCustomerDetails updateCustomerDetails) throws UserNotFoundException {
        Customer customerToUpdate = customerRepository.findById(updateCustomerDetails.getCustomerId()).orElseThrow(
                ()-> new UserNotFoundException(String.format("user with id %d not found", updateCustomerDetails.getCustomerId())));
        log.info("initial details before update::{}", customerToUpdate);
        modelMapper.map(updateCustomerDetails, customerToUpdate);
        log.info("updated details -->{}", customerToUpdate);
        var customerToAddressList = customerToUpdate.getAddress();
        var foundAddress = customerToAddressList.stream().findFirst();
        if (foundAddress.isPresent()) applyAddressUpdate(updateCustomerDetails, foundAddress.get());
        customerToUpdate.getAddress().add(foundAddress.get());
        var savedUpdate = customerRepository.save(customerToUpdate);
        log.info("updated details -->{}", customerToUpdate);
        return String.format("%s details updated successfully", updateCustomerDetails.getFirstName());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    private void applyAddressUpdate(UpdateCustomerDetails updateCustomerDetails, Address address) {
        address.setBuildingNumber(updateCustomerDetails.getBuildingNumber());
        address.setStreet(updateCustomerDetails.getStreet());
        address.setCity(updateCustomerDetails.getCity());
        address.setState(updateCustomerDetails.getState());
    }

}
