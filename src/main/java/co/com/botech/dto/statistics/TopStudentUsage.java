package co.com.botech.dto.statistics;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopStudentUsage {
    private Long studentSchoolId;
    private String studentName;
    private String grade;
    private Long totalRegisters;
    private Long entryRegisters;
    private Long exitRegisters;
    private LocalDateTime lastRegisterDateTime;
}
