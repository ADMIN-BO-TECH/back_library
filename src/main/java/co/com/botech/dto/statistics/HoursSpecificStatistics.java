package co.com.botech.dto.statistics;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoursSpecificStatistics {
    private String hourRange;
    private Long totalRegisters;
    private Long  totalStudentRegisters;
    private Long totalEmployeeRegisters;
    private Long totalParentsRegisters;
    private Long totalExternalRegisters;
}
