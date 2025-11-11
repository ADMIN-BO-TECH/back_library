package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "stop")
public class Stop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_id")
    private Long id;

    @Column(name="latitude")  private Double latitude;
    @Column(name="longitude") private Double longitude;
    @Column(name="address")   private String address;
    @Column(name="status")    private Boolean status;
    @Column(name="time")      private Integer time;
    @Column(name="stop_order")private Integer stopOrder;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "stop_types_id")
    private StopType stopType;
}
