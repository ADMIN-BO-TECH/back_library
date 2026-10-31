package co.com.botech.dto.student.tracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student pickup preferences update")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentPreferencesRequest {
    @Schema(description = "Student record ID (student_record_id)", example = "1024")
    private Long studentRecordId;
    @Schema(description = "Whether the student can leave the bus alone", example = "false")
    private Boolean leaveAlone;
    @Schema(description = "Whether the student can board the bus alone", example = "false")
    private Boolean pickUpAlone;
}