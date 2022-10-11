package africa.semicolon.lumExpress.services.notification;

import africa.semicolon.lumExpress.data.dtos.request.EmailNotificationRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmailNotificationServiceImplTest {
    @Autowired
    private iEmailNotificationService emailSender;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sendHtmlMail() {
        EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest();
        emailNotificationRequest.setUserEmail("chat.with.ucjhay@gmail.com");
        emailNotificationRequest.setMailContent("Hi, whimp!! this is a test mail for spring lum express application");
        var response = emailSender.sendHtmlMail(emailNotificationRequest);
        assertThat(response.contains("successfully")).isTrue();
    }
}