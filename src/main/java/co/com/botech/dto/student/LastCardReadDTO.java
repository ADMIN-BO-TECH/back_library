package co.com.botech.dto.student;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Last card read")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LastCardReadDTO {
    @Schema(description = "Date time")
    private String dateTime;
    @Schema(description = "Register type")
    private String registerType;
    @Schema(description = "Reader description")
    private String readerDescription;
    @Schema(description = "Read on the reference date")
    private boolean readOnReferenceDate;
}
