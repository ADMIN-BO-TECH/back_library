package co.com.botech.dto.workBlock;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Employee weekly schedule changes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SchedulePatchRequest {

    @Schema(description = "Week start date (Monday, yyyy-MM-dd)", example = "2026-10-19")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate weekStart;

    @Schema(description = "Changes for the week")
    @NotEmpty
    private List<@Valid ScheduleChangeDTO> changes;
}