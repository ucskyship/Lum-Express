package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class NotificationRequest {
    private Long userId;
    private String messageBody;
}
