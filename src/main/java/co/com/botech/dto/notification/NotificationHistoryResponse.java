package co.com.botech.dto.notification;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationHistoryResponse{
    private Long id;
    private String title;
    private String body;
    private String categoryName;
    private LocalDateTime sentAt;
}

