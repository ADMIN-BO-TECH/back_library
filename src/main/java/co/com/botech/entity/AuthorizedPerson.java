package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "authorized_persons")
public class AuthorizedPerson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorized_person_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @Column(name = "document_number") private String documentNumber;
    @Column(name = "first_name")      private String firstName;
    @Column(name = "last_name")       private String lastName;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "family_code_id")
    private FamilyCode familyCode;

    @Column(name = "authorization_start") private LocalDateTime authorizationStart;
    @Column(name = "authorization_end")   private LocalDateTime authorizationEnd;

    @Column(name = "authorized_by") private String authorizedBy;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "school_id")
    private School school;
}
