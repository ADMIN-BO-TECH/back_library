package co.com.botech.dto.statistics;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeUsage {
    private String grade;
    private Long totalRegisters;
    private Long studentWithRegisters;
    private Double usagePercentage;
}
