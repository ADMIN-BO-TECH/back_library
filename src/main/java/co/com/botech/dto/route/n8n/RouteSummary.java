package co.com.botech.dto.route.n8n;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSummary {
    private Long routeId;
    private String name;
    private String status;
}
