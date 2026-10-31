package co.com.botech.dto.statistics;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Grade usage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeUsage {
    @Schema(description = "Grade")
    private String grade;
    @Schema(description = "Total registers")
    private Long totalRegisters;
    @Schema(description = "Student with registers")
    private Long studentWithRegisters;
    @Schema(description = "Usage percentage")
    private Double usagePercentage;
}
