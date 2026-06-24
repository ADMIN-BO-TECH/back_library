package co.com.botech.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import co.com.botech.constants.WorkBlockSource;
import co.com.botech.constants.WorkBlockStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "work_blocks")
public class WorkBlock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "work_date")
    private LocalDate workDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "start_lat")
    private Double startLat;

    @Column(name = "start_lng")
    private Double startLng;

    @Column(name = "end_lat")
    private Double endLat;

    @Column(name = "end_lng")
    private Double endLng;

    @Column(name = "is_complete")
    private Boolean isComplete;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private WorkBlockSource source;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private WorkBlockStatus status;

    @Column(name = "source_start_attendance_id")
    private Long sourceStartAttendanceId;

    @Column(name = "source_end_attendance_id")
    private Long sourceEndAttendanceId;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "extra_hour", nullable = false)
    private Boolean extraHour;

    @Column(name = "minutes_extra")
    private Integer minutesExtra;

    @Column(name = "nigth_hours", nullable = false)
    private Boolean nigthHours;

    @Column(name = "minutes_nigth_hours")
    private Integer minutesNigthHours;
}