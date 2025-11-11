package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "employees_details")
public class EmployeeDetail {
    @Id
    @Column(name = "employee_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) @MapsId
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
