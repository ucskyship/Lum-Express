package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.exceptions.VerificationTokenException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class VerificationTokenRepositoryTest {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private VerificationToken verificationToken;

    @BeforeEach
    void setUp() {
        verificationToken = new VerificationToken();
        verificationToken.setToken("12345");
        verificationToken.setUserEmail("test@gmail.com");
    }

    @AfterEach
    void tearDown() {
        verificationTokenRepository.deleteAll();
    }

    @Test
    void findByUserEmail() throws VerificationTokenException {
        verificationTokenRepository.save(verificationToken);
        var foundToken = verificationTokenRepository.findByUserEmail("test@gmail.com").orElseThrow(
                () -> new VerificationTokenException("token not found"));
        log.info("found token-->{}", foundToken);
        assertThat(foundToken).isNotNull();
        assertThat(foundToken.getToken()).isEqualTo(verificationToken.getToken());
    }

    @Test
    void findByToken() {
    }
}
