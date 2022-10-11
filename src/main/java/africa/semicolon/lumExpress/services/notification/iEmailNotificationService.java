package africa.semicolon.lumExpress.services.notification;

import africa.semicolon.lumExpress.data.dtos.request.EmailNotificationRequest;

public interface iEmailNotificationService {
    String sendHtmlMail(EmailNotificationRequest emailNotificationRequest);
}
