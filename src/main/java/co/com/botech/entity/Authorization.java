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
@Table(name = "authorizations")
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorization_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorized_person_id")
    private AuthorizedPerson authorizedPerson;

    @Column(name = "authorization_start_date")
    private LocalDate authorizationStartDate;
    @Column(name = "authorization_end_date")
    private LocalDate authorizationEndDate;

    @Column(name = "authorized_by")
    private String authorizedBy;
}