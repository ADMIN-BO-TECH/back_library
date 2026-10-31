package co.com.botech.dto.statistics;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Last week usage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LastWeekUsage {
    @Schema(description = "Date")
    private String date;
    @Schema(description = "Total registers")
    private Long totalRegisters;
    @Schema(description = "Total student registers")
    private Long totalStudentRegisters;
    @Schema(description = "Total employee registers")
    private Long totalEmployeeRegisters;
    @Schema(description = "Total parents registers")
    private Long totalParentsRegisters;
    @Schema(description = "Total external registers")
    private Long totalExternalRegisters;
}
