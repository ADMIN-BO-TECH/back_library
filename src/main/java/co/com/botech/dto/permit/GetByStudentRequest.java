package co.com.botech.dto.permit;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student permits search")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetByStudentRequest {
    @Schema(description = "Student record ID (student_record_id); empty returns all permits", example = "1024")
    private String studentRecordId;
}
