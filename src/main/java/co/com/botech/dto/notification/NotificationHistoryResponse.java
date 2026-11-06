package co.com.botech.dto.notification;

import lombok.*;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Notification history")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationHistoryResponse {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Title")
    private String title;
    @Schema(description = "Message body")
    private String body;
    @Schema(description = "Category name")
    private String categoryName;
    @Schema(description = "Sent at")
    private LocalDateTime sentAt;

}

