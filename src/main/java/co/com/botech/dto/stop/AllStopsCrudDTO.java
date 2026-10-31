package co.com.botech.dto.stop;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Stop with assigned people")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AllStopsCrudDTO {
    @Schema(description = "Stop information")
    private StopDTO stopInformation;
    @Schema(description = "Students assigned to the stop")
    private List<PersonCrudResponse> students;
    @Schema(description = "School employees assigned to the stop")
    private List<PersonCrudResponse> schoolEmployees;
}
