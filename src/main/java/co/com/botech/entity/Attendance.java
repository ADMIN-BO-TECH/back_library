package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @Column(name = "user_type")
    private String userType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rfid_register_id")
    private RfidRegister rfidRegister;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_type_id")
    private AttendanceType type;

    @Column(name = "attendance_time")
    private LocalDateTime attendanceTime;

    @Column(name = "system_name")
    private String system;

    @Column(name = "register_type")
    private String registerType;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    // FKs opcionales (PERMITE NULL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_record_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private SchoolEmployee schoolEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorized_person_id")
    private AuthorizedPerson authorizedPerson;
}