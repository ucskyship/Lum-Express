package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Cart;
import africa.semicolon.lumExpress.data.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByEmail() {
        var customer = Customer.builder()
                .cart(new Cart())
                .build();
        customer.setEmail("test@gmail.com");
        customer.setFirstname("Josh");
        customer.setLastName("Joe");
        customer.setPassword("test password");
        var savedCustomer = customerRepository.save(customer);
        log.info("saved customer::{}", savedCustomer);

        assertThat(customerRepository.findByEmail(savedCustomer.getEmail())).isNotNull();
        assertEquals("test@gmail.com", savedCustomer.getEmail());
    }
}
