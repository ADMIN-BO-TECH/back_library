package co.com.botech.dto.workBlock;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Work block item")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockItemDTO {
    @Schema(description = "ID")
    private Long    id;
    @Schema(description = "Block has an exit time")
    private Boolean isComplete;
    @Schema(description = "Includes extra hours")
    private Boolean extraHour;
    @Schema(description = "Extra minutes")
    private Integer minutesExtra;
    @Schema(description = "Includes night hours")
    private Boolean nigthHours;
    @Schema(description = "Night minutes")
    private Integer minutesNigthHours;
    @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate blockDate;
    @JsonFormat(pattern = "HH:mm:ss") private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm:ss") private LocalTime endTime;
    @Schema(description = "Duration minutes")
    private Integer     durationMinutes;
    @Schema(description = "Start location")
    private LocationDTO startLocation;
    @Schema(description = "End location")
    private LocationDTO endLocation;
}
