package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "document_type")
public class DocumentType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_type_id")
    private Long id;

    @Column(name = "code") private String code;
    @Column(name = "name") private String name;
}
