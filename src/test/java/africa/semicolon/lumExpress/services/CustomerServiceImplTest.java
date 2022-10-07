package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.services.customerService.iCustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    iCustomerService customerService;

    private CustomerRegistrationRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = CustomerRegistrationRequest
                .builder()
                .email("test@gmail.com")
                .password("123456")
                .country("Nigeria")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        var customerRegistrationResponse = customerService.register(registerRequest);
        assertThat(customerRegistrationResponse).isNotNull();
        assertThat(customerRegistrationResponse.getMessage()).isNotNull();
        assertThat(customerRegistrationResponse.getUserId()).isGreaterThan(0);
        assertThat(customerRegistrationResponse.getCode()).isEqualTo(201);
    }

    @Test
    void login() {
    }

    @Test
    void completeProfile() {
    }
}