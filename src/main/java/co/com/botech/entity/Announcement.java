package co.com.botech.entity;

import co.com.botech.constants.AnnouncementStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "announcements", indexes = {
        @Index(name = "idx_announcements_school_id", columnList = "school_id"),
        @Index(name = "idx_announcements_publish_date", columnList = "publish_date"),
        @Index(name = "idx_announcements_status", columnList = "status"),
        @Index(
                name = "idx_announcements_school_status_publish",
                columnList = "school_id, status, publish_date"
        )
})
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @NotBlank
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "tags", columnDefinition = "TEXT")
    private String tags;

    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @NotBlank
    @Column(name = "author_firebase_uid", length = 128, nullable = false)
    private String authorFirebaseUid;

    @NotBlank
    @Column(name = "author_name", length = 150, nullable = false)
    private String authorName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private AnnouncementStatus status;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;
        if (status == null) {
            status = AnnouncementStatus.DRAFT;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}



