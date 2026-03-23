package co.com.botech.dto.route.n8n;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActiveRouteSummary {
    private Long routeId;
    private String name;
    private String status;
}
