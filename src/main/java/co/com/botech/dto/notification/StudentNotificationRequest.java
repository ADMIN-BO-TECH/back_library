package co.com.botech.dto.notification;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Notification to parents by student")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentNotificationRequest {
    @Schema(description = "Student school IDs (student_id), not student_record_id", example = "[20231045, 20231046]")
    List<Long> studentIdsList;
    @Schema(description = "Notification content")
    NotificationBodyRequest request;
}
