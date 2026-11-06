package co.com.botech.dto.notification;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Notification to parents by grade")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeNotificationRequest {
    @Schema(description = "Grades to notify; must match gradeLevel exactly", example = "[\"5A\", \"5B\"]")
    List<String> gradesList;
    @Schema(description = "Notification content")
    NotificationBodyRequest request;
}
