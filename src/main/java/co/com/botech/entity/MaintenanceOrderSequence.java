package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

/** Consecutivo anual del código OM-AAAA-NNNN. Se lee con bloqueo pesimista al crear la orden. */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "maintenance_order_sequence")
public class MaintenanceOrderSequence {

    @Id
    @Column(name = "seq_year")
    private Integer seqYear;

    // last_value es palabra reservada en MySQL 8 (función de ventana)
    @Column(name = "last_number", nullable = false)
    private Integer lastValue;
}
