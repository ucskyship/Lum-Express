package africa.semicolon.lumExpress.services.notification;

import africa.semicolon.lumExpress.data.dtos.request.NotificationRequest;

public interface iNotificationService {
    String sendNotification(NotificationRequest request);
}
