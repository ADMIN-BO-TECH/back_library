package co.com.botech.dto.linkManagement;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLinkManagementRequest {
    private Long linkId;
    private String description;
    private boolean visibleInApp;
}

