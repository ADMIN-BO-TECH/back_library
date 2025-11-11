package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "started_routes_register")
public class StartedRouteRegister {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "route_id")
    private Route route;

    @Column(name="route_name") private String routeName;
    @Column(name="route_days") private String routeDays;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name="status") private Boolean status;

    @Column(name="assistant_id") private Integer assistantId; // si luego enlaza a Employees, migramos
    @Column(name="operator_id")  private Integer operatorId;

    @Column(name="start_time") private String startTime;
    @Column(name="end_time")   private String endTime;
    @Column(name="route_type") private String routeType;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "school_id")
    private School school;
}
