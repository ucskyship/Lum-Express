package africa.semicolon.lumExpress.services.customerService;

import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumExpress.data.dtos.response.CustomerRegistrationResponse;
import africa.semicolon.lumExpress.services.customerService.iCustomerService;
import africa.semicolon.lumExpress.utils.LumExpressUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
//@Sql(scripts = "insert")
class CustomerServiceImplTest {
    @Autowired
    iCustomerService customerService;
    private CustomerRegistrationRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = CustomerRegistrationRequest
                .builder()
                .email("fanalas844@inkmoto.com")
                .password("123456")
                .country("Nigeria")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerCustomerTest() {
        var customerRegistrationResponse = customerService.register(registerRequest);
        assertThat(customerRegistrationResponse).isNotNull();
        assertThat(customerRegistrationResponse.getMessage()).isNotNull();
        assertThat(customerRegistrationResponse.getUserId()).isGreaterThan(0);
        assertThat(customerRegistrationResponse.getCode()).isEqualTo(201);
    }

    @Test
    void completeCustomerProfileTest() {
        CustomerRegistrationResponse response = customerService.register(registerRequest);
        UpdateCustomerDetails updateDetails = UpdateCustomerDetails
                .builder()
                .customerId(response.getUserId())
                .imageUrl(LumExpressUtils.getMockCloudinaryImageUrl())
                .lastName("test lastname")
                .phoneNumber("99999999")
                .city("Yaba")
                .state("Lagos")
                .street("Herbert Marculy")
                .buildingNumber(312)
                .build();
        var updateResponse = customerService.completeCustomerProfile(updateDetails);
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.contains("success")).isTrue();
    }
}