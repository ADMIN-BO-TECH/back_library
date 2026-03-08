package co.com.botech.dto.linkManagment;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkManagementAppResponse {
    private Long id;
    private String linkUrl;
    private String description;
    private LocalDateTime updatedAt;
    private String routeId;
    private String routeName;
}
