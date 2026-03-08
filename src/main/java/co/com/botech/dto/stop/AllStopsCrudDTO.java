package co.com.botech.dto.stop;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AllStopsCrudDTO {
    private StopDTO stopInformation;
    private List<PersonCrudResponse> students;
    private List<PersonCrudResponse> schoolEmployees;
}
