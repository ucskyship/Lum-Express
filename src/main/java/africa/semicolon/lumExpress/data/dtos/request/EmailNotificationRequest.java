package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmailNotificationRequest {
    private String userEmail;
    private String mailContent;
}
