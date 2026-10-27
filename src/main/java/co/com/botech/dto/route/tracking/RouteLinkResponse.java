package co.com.botech.dto.route.tracking;

import lombok.*;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RouteLinkResponse {
    private RouteLinkData route;
    private List<StopDtoTracking> stops;
    private Boolean showUbication;
}