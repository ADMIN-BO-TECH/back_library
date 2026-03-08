package co.com.botech.dto.stop;

import co.com.botech.util.utilObjects.GroupedStopRelationInterface;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PersonGroupedResponse implements GroupedStopRelationInterface {
    private Long relationId;
    private Long personId;
    private String name;
    private String personType;
}
