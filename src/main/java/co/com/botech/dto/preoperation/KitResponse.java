package co.com.botech.dto.preoperation;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Kit")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KitResponse {
    @Schema(description = "Items")
    private List<KitItemBreakdownDTO> items;
}
