package co.com.botech.dto.notification;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeNotificationRequest {
    List<String> gradesList;
    NotificationBodyRequest request;
}
