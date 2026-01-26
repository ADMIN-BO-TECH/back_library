package co.com.botech.dto.statistics;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LastWeekUsage {
    private String date;
    private Long totalRegisters;
    private Long totalStudentRegisters;
    private Long totalEmployeeRegisters;
    private Long totalParentsRegisters;
    private Long totalExternalRegisters;
}
