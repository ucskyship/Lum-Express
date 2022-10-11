package africa.semicolon.lumExpress.services.notification;

import africa.semicolon.lumExpress.data.dtos.request.NotificationRequest;

public interface iLumExpressNotificationService {
    String sendNotification(NotificationRequest request);
}
