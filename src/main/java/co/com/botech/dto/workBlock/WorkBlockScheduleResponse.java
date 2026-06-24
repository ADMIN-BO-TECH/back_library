package co.com.botech.dto.workBlock;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockScheduleResponse {

    @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate weekStart;
    @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate weekEnd;

    @JsonProperty("SUNDAY")    private List<WorkBlockItemDTO> sunday;
    @JsonProperty("MONDAY")    private List<WorkBlockItemDTO> monday;
    @JsonProperty("TUESDAY")   private List<WorkBlockItemDTO> tuesday;
    @JsonProperty("WEDNESDAY") private List<WorkBlockItemDTO> wednesday;
    @JsonProperty("THURSDAY")  private List<WorkBlockItemDTO> thursday;
    @JsonProperty("FRIDAY")    private List<WorkBlockItemDTO> friday;
    @JsonProperty("SATURDAY")  private List<WorkBlockItemDTO> saturday;
}
