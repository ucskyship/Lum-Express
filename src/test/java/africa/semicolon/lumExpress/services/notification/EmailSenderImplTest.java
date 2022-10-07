package africa.semicolon.lumExpress.services.notification;

import africa.semicolon.lumExpress.data.models.EmailDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailSenderImplTest {
    @Autowired
    private iEmailSender emailSender;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sendHtmlMail() {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setUserEmail("chat.with.ucjhay@gmail.com");
        emailDetails.setMailContent("Hi, whimp!! this is a test mail for spring lum express application");
        var response = emailSender.sendHtmlMail(emailDetails);
        assertThat(response.contains("successfully")).isTrue();
    }
}