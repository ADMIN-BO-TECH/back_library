package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "school_geofences")
public class SchoolGeofences {
    @Id
    @Column(name = "school_id")
    private Long schoolId;

    @OneToOne(fetch = FetchType.LAZY) @MapsId
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name="latitude_1")  private Double latitude1;
    @Column(name="longitude_1") private Double longitude1;
    @Column(name="latitude_2")  private Double latitude2;
    @Column(name="longitude_2") private Double longitude2;
    @Column(name="latitude_3")  private Double latitude3;
    @Column(name="longitude_3") private Double longitude3;
    @Column(name="latitude_4")  private Double latitude4;
    @Column(name="longitude_4") private Double longitude4;
}