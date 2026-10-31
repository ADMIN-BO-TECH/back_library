package co.com.botech.dto.preoperation;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "External inspection")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExternalInspectionResponse {
    @Schema(description = "Components")
    private List<ExternalComponentBreakdownDTO> components;
}
