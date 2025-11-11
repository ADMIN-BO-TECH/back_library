package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "parent")
public class Parent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @Column(name = "document_number") private String documentNumber;
    @Column(name = "first_name")      private String firstName;
    @Column(name = "last_name")       private String lastName;
    @Column(name = "phone_number")    private String phoneNumber;
    @Column(name = "email")           private String email;

    @Column(name = "relationship_type")
    private String relationshipType;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "family_code_id")
    private FamilyCode familyCode;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "school_id")
    private School school;
}