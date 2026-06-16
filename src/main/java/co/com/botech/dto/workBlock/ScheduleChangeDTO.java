package co.com.botech.dto.workBlock;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ScheduleChangeDTO {

    @NotBlank
    private String operation;   // "CREATE" | "UPDATE" | "DELETE"

    private Long id;            // requerido en UPDATE y DELETE

    private String day;         // requerido en CREATE: nombre DayOfWeek en inglés (ej. "TUESDAY")

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @Valid private LocationDTO startLocation;
    @Valid private LocationDTO endLocation;
}