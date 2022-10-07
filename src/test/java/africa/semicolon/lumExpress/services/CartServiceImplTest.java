package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.services.cartService.iCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CartServiceImplTest {
    @Autowired
    private iCartService cartService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("test that cart can be created")
    void creatCartTest() {
//        var cart = cartService.create();
//        assertThat(cart).isNotNull();
    }
}
