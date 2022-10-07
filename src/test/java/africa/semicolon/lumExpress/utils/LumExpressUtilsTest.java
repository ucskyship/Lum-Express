package africa.semicolon.lumExpress.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class LumExpressUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateTokenTest() {
        String token = LumExpressUtils.generateToken();
        log.info("you token is-->{}", token);
        assertThat(token).isNotNull();
        assertThat(token.length()).isEqualTo(5);
    }
}