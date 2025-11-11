package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "rfid_register")
public class RfidRegister {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rfid_register_id")
    private Long id;

    @Column(name = "rfid_tag")    private String rfidTag;
    @Column(name = "description") private String description;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "kind_device_id")
    private KindDevice kindDevice;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "school_id")
    private School school;
}