package co.com.botech.dto.attendance;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Historic attendance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricAttendanceResponse {
    @Schema(description = "Total registers")
    private Long totalRegisters;
    @Schema(description = "Total pages")
    private int totalPages;
    @Schema(description = "Registers")
    private List<AttendancePageResponse> registers;
}
