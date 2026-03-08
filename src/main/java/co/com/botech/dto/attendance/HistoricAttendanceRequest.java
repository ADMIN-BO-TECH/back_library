package co.com.botech.dto.attendance;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricAttendanceRequest {
    @NotNull(message = "La paginación no puede ser nula")
    private int pagination;
    private int pageSize;
    private FilterRequest filters;
}
