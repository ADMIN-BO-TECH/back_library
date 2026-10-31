package co.com.botech.dto.statistics;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Hours specific statistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoursSpecificStatistics {
    @Schema(description = "Hour range")
    private String hourRange;
    @Schema(description = "Total registers")
    private Long totalRegisters;
    @Schema(description = "Total student registers")
    private Long  totalStudentRegisters;
    @Schema(description = "Total employee registers")
    private Long totalEmployeeRegisters;
    @Schema(description = "Total parents registers")
    private Long totalParentsRegisters;
    @Schema(description = "Total external registers")
    private Long totalExternalRegisters;
}
