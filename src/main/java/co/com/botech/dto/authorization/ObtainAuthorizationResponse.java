package co.com.botech.dto.authorization;

import lombok.*;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Obtain authorization")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObtainAuthorizationResponse {
    @Schema(description = "ID authorization")
    private Long idAuthorization;
    @Schema(description = "ID student")
    private Long idStudent;
    @Schema(description = "Student name")
    private String studentName;
    @Schema(description = "Student grade")
    private String studentGrade;
    @Schema(description = "Authorized person name")
    private String authorizedPersonName;
    @Schema(description = "Authorization start")
    private LocalDate authorizationStart;
    @Schema(description = "Authorization end")
    private LocalDate authorizationEnd;
    @Schema(description = "Authorized by")
    private String authorizedBy;
}
