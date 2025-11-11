package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "stop_information")
public class StopInformation {
    @Id
    @Column(name = "stop_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) @MapsId
    @JoinColumn(name = "stop_id")
    private Stop stop;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "student_record_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "state")
    private String state;
}
