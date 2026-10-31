package co.com.botech.dto.workBlock;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Weekly schedule work block change")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ScheduleChangeDTO {

    @Schema(description = "Allowed: CREATE, UPDATE, DELETE", example = "UPDATE")
    @NotBlank
    private String operation;   // "CREATE" | "UPDATE" | "DELETE"

    @Schema(description = "Work block ID; required for UPDATE and DELETE", example = "140")
    private Long id;            // requerido en UPDATE y DELETE

    @Schema(description = "Weekday in English (MONDAY to SUNDAY); required for CREATE", example = "MONDAY")
    private String day;         // requerido en CREATE: nombre DayOfWeek en inglés (ej. "TUESDAY")

    @Schema(description = "Start time (HH:mm:ss); required for CREATE", example = "06:00:00")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @Schema(description = "End time (HH:mm:ss); optional", example = "14:00:00")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @Schema(description = "Block start location")
    @Valid private LocationDTO startLocation;
    @Schema(description = "Block end location")
    @Valid private LocationDTO endLocation;
}