package co.com.botech.dto.notification;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private List<String> token;
    private String title;
    private String topic;
    private String body;
}
