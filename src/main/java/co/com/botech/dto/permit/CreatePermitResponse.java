package co.com.botech.dto.permit;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Create permit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePermitResponse {
    @Schema(description = "Result message")
    private String message;
    @Schema(description = "Email")
    private String email;
    @Schema(description = "Permit was approved")
    private Boolean approved;
}