package co.com.botech.dto.notification;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationBodyRequest {
    private String title;
    private String body;
    private String email;
    private String notificationCategory;
}
