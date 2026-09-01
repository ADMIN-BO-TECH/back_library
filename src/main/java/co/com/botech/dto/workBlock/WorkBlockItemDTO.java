package co.com.botech.dto.workBlock;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockItemDTO {
    private Long    id;
    private Boolean isComplete;
    private Boolean extraHour;
    private Integer minutesExtra;
    private Boolean nigthHours;
    private Integer minutesNigthHours;
    @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate blockDate;
    @JsonFormat(pattern = "HH:mm:ss") private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm:ss") private LocalTime endTime;
    private Integer     durationMinutes;
    private LocationDTO startLocation;
    private LocationDTO endLocation;
}
