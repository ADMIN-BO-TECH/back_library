package co.com.botech.dto.student;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Leave alone")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveAloneResponse {
    @Schema(description = "Reference date")
    private String referenceDate;
    @Schema(description = "Timezone")
    private String timezone;
    @Schema(description = "Total")
    private int total;
    @Schema(description = "Items")
    private List<LeaveAloneStudentDTO> items;
}
