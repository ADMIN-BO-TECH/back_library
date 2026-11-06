package co.com.botech.dto.workBlock;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Work blocks of a week, grouped by day")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockScheduleResponse {

    @Schema(description = "Week start date")
    @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate weekStart;
    @Schema(description = "Week end date")
    @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate weekEnd;

    @Schema(description = "Sunday work blocks")
    @JsonProperty("SUNDAY")    private List<WorkBlockItemDTO> sunday;
    @Schema(description = "Monday work blocks")
    @JsonProperty("MONDAY")    private List<WorkBlockItemDTO> monday;
    @Schema(description = "Tuesday work blocks")
    @JsonProperty("TUESDAY")   private List<WorkBlockItemDTO> tuesday;
    @Schema(description = "Wednesday work blocks")
    @JsonProperty("WEDNESDAY") private List<WorkBlockItemDTO> wednesday;
    @Schema(description = "Thursday work blocks")
    @JsonProperty("THURSDAY")  private List<WorkBlockItemDTO> thursday;
    @Schema(description = "Friday work blocks")
    @JsonProperty("FRIDAY")    private List<WorkBlockItemDTO> friday;
    @Schema(description = "Saturday work blocks")
    @JsonProperty("SATURDAY")  private List<WorkBlockItemDTO> saturday;
}
