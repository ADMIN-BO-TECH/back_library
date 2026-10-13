package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "employee_guest_authorizations")
public class EmployeeGuestAuthorization {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_guest_authorization_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "authorized_person_id", nullable = false)
    private AuthorizedPerson authorizedPerson;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private SchoolEmployee schoolEmployee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "authorization_start", nullable = false)
    private LocalDate authorizationStart;
    @Column(name = "authorization_end", nullable = false)
    private LocalDate authorizationEnd;

    @Column(name = "description")  private String description;
    @Column(name = "authorized_by") private String authorizedBy;

    @Builder.Default
    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean active = true;

    @Column(name = "created_at") private LocalDateTime createdAt;
    @Column(name = "updated_at") private LocalDateTime updatedAt;
}
