package co.com.botech.dto.stop;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Person assigned to a stop")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PersonCrudResponse {
    @Schema(description = "Student or employee ID", example = "1024")
    private Long personRecordId;
    @Schema(description = "Person's full name", example = "Juan Pérez")
    private String personName;
}
