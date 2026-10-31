package co.com.botech.dto.permit;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Permit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermitResponse {
    @Schema(description = "Permit ID")
    private Long permitId;
    @Schema(description = "Student school ID")
    private Long studentSchoolId ;
    @Schema(description = "Created date")
    private LocalDateTime createdDate;
    @Schema(description = "Permit date")
    private LocalDate permitDate;
    @Schema(description = "Requested by")
    private String requestedBy;
    @Schema(description = "Description")
    private String description;
    @Schema(description = "Reply to the permit")
    private String response;
    @Schema(description = "Replied by")
    private String repliedBy;
    @Schema(description = "Permit status")
    private String permitStatus;
    @Schema(description = "Permit type")
    private String permitType;
}
