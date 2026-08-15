package co.com.botech.dto.notification;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationPayload {

    private String title;

    private String body;

    private String imageUrl;

    private Map<String, String> data;

    private String categoryName;
}
