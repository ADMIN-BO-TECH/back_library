package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "vehicles")
public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "plate_number")  private String plateNumber;
    @Column(name = "fleet_number")  private String fleetNumber;
    @Column(name = "rfid_tag")      private String rfidTag;
}