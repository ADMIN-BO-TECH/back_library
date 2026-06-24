package co.com.botech.dto.workBlock;

import lombok.*;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockHistoricResponse {
    private List<WorkBlockItemDTO> blocks;
    private Long totalMinutes;
    private String totalHours;  // formato "HH:mm"
}