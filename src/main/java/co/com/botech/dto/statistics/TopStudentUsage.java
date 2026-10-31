package co.com.botech.dto.statistics;

import lombok.*;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Top student usage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopStudentUsage {
    @Schema(description = "Student school ID")
    private Long studentSchoolId;
    @Schema(description = "Student name")
    private String studentName;
    @Schema(description = "Grade")
    private String grade;
    @Schema(description = "Total registers")
    private Long totalRegisters;
    @Schema(description = "Entry registers")
    private Long entryRegisters;
    @Schema(description = "Exit registers")
    private Long exitRegisters;
    @Schema(description = "Last register date time")
    private LocalDateTime lastRegisterDateTime;
}
