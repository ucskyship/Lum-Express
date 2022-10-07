package africa.semicolon.lumExpress.services.notification;

import africa.semicolon.lumExpress.data.models.EmailDetails;

public interface iEmailSender {
    String sendHtmlMail(EmailDetails emailDetails);
}
