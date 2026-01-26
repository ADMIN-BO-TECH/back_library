package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "permits")
public class Permits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permit_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "permit_date")
    private LocalDate permitDate;

    @Column(name = "requested_by")
    private String requestedBy;
    @Column(name = "response")
    private String response;

    @Column(name = "replied_by")
    private String repliedBy;

    @Column(name = "permit_type")
    private String permitType;

    @Column(name = "permit_status")
    private String permitStatus;
}
