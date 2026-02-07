package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "route_waypoints")
public class RouteWaypoints {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waypoint_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "route_id")
    private Route route;

    @Column(name="latitude")  private Double latitude;
    @Column(name="longitude") private Double longitude;
    @Column(name="address")   private String address;
    @Column(name="order_index") private Integer orderIndex;
    @Column(name = "specific_order") private Integer specificOrder;
    @Column(name="name")        private String name;
}