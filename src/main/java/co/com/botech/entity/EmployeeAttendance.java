package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "employee_attendance")
public class EmployeeAttendance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "rfid_register_id") private String rfidRegisterId;
    @Column(name = "attendance_time")  private LocalDateTime attendanceTime;
    @Column(name = "latitude")         private Double latitude;
    @Column(name = "longitude")        private Double longitude;
}
