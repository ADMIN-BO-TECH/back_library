package co.com.botech.dto.student;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Summary section")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummarySectionResponse {
    @Schema(description = "ID")
    private String id;
    @Schema(description = "Items")
    private List<Object> items;
}
