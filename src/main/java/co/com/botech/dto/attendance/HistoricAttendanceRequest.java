package co.com.botech.dto.attendance;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Map;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Paginated attendance history search")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricAttendanceRequest {
    @Schema(description = "Page number, starts at 1", example = "1")
    @NotNull(message = "La paginación no puede ser nula")
    private int pagination;
    @Schema(description = "Records per page; 0 means 10", example = "20")
    private int pageSize;
    @Schema(description = "Search filters")
    private FilterRequest filters;
}
