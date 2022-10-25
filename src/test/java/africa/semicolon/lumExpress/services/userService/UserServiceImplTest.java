package africa.semicolon.lumExpress.services.userService;

import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.data.dtos.request.LoginRequest;
import africa.semicolon.lumExpress.services.customerService.iCustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    iCustomerService customerService;
    @Autowired
    private iUserService userService;
    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        loginRequest = LoginRequest.builder()
                .email("fanalas844@inkmoto.com")
                .password("123456")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loginTest() {
        CustomerRegistrationRequest registerRequest = CustomerRegistrationRequest
                .builder()
                .email("fanalas844@inkmoto.com")
                .password("123456")
                .country("Nigeria")
                .build();
        customerService.register(registerRequest);
        var response = userService.login(loginRequest);
        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(200);
    }
}
