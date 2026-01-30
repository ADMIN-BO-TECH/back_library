package co.com.botech.dto.route;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeactivateRouteRequest {
    private List<Long> routeIds;
}