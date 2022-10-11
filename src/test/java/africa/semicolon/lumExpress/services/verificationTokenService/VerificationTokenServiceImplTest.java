package africa.semicolon.lumExpress.services.verificationTokenService;

import africa.semicolon.lumExpress.data.models.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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
    void createTokenTest() {
        VerificationToken verificationToken = verificationTokenService.createToken("test@gmail.com");
        log.info("verified token object::{}", verificationToken);
        assertThat(verificationToken).isNotNull();
        assertThat(verificationToken.getToken().length()).isEqualTo(5);
        assertThat(verificationToken.getUserEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    void isValidVerificationTokenTest(){
        var verificationToken = verificationTokenService.createToken("test@gmail.com");
        assertThat(verificationToken).isNotNull();
        var response = verificationTokenService.isValidVerificationToken(verificationToken.getToken());
        assertThat(response).isTrue();
    }
}
