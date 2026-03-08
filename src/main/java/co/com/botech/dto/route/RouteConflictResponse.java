package co.com.botech.dto.route;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteConflictResponse {
    private Long routeId;
    private String routeName;
    private String schedule;
    private String vehicle;
    private String assistant;
    private String operator;
}