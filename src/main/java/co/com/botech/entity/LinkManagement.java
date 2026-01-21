package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "link_management")
public class LinkManagement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Long id;

    @Column(name="status")       private Boolean status;
    @Column(name="link_url")     private String linkUrl;
    @Column(name="visible_in_app") private Boolean visibleInApp;
    @Column(name="description")  private String description;
    @Column(name="access_key")   private String accessKey;
    @Column(name="updated_at") private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "route_id")
    private Route route;
}
