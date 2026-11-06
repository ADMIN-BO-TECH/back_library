package co.com.botech.dto.workBlock;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Work block historic")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockHistoricResponse {
    @Schema(description = "Blocks")
    private List<WorkBlockItemDTO> blocks;
    @Schema(description = "Total minutes")
    private Long totalMinutes;
    @Schema(description = "Total hours")
    private String totalHours;  // formato "HH:mm"
}