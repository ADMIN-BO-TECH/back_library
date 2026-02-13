package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_record_id")
    private Long id;

    @Column(name = "student_id") private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "first_name")    private String firstName;
    @Column(name = "last_name")     private String lastName;
    @Column(name = "home_address")  private String homeAddress;
    @Column(name = "grade_level")   private String gradeLevel;
    @Column(name = "rfid_tag")      private String rfidTag;
    @Column(name = "transport")     private String transport;
    @Column(name = "pickup_alone")  private Boolean pickupAlone;
    @Column(name = "leave_alone")   private Boolean leaveAlone;
    @Builder.Default @Column(name = "novedad", nullable = false)
    private Boolean novelty = false;
}
