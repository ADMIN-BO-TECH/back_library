package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "employees")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @Column(name = "document_number") private String documentNumber;
    @Column(name = "first_name")      private String firstName;
    @Column(name = "email")           private String email;
    @Column(name = "position")        private String position;
    @Column(name = "rfid_tag")        private String rfidTag;
}
