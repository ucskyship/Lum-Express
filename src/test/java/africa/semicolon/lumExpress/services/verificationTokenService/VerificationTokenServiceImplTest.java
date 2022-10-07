package africa.semicolon.lumExpress.services.verificationTokenService;

import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.data.repositories.VerificationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class VerificationTokenServiceImplTest {
    @Autowired
    private iVerificationTokenService verificationTokenService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateVerificationTokenTest() {
        VerificationToken verificationToken = verificationTokenService.generateVerificationToken("test@gmail.com");
        log.info("verified token::{}", verificationToken);
        assertThat(verificationToken).isNotNull();
        assertThat(verificationToken.getToken().length()).isEqualTo(5);
    }
}