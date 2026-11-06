package co.com.botech.dto.student;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student summary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentSummaryResponse {
    @Schema(description = "Student school ID")
    private Long studentSchoolId;
    @Schema(description = "Sections")
    private List<SummarySectionResponse> sections;
}
