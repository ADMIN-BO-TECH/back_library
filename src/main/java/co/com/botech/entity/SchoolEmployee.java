package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "school_employees")
public class SchoolEmployee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @Column(name = "document_number") private String documentNumber;
    @Column(name = "first_name")      private String firstName;
    @Column(name = "last_name")       private String lastName;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "family_id")
    private Family family;

    @Column(name = "email")    private String email;
    @Column(name = "position") private String position;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "school_id")
    private School school;
}
