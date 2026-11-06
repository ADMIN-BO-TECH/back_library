package co.com.botech.dto.authorization.tracking;

import lombok.*;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Obtain authorization tracking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObtainAuthorizationTrackingResponse {
    @Schema(description = "ID authorization")
    private Long idAuthorization;
    @Schema(description = "Student record ID")
    private Long studentRecordId;
    @Schema(description = "Student ID")
    private Long studentId;
    @Schema(description = "Student name")
    private String studentName;
    @Schema(description = "Student grade")
    private String studentGrade;
    @Schema(description = "Authorized person name")
    private String authorizedPersonName;
    @Schema(description = "Authorized person document type")
    private String authorizedPersonDocumentType;
    @Schema(description = "Authorized person document number")
    private String authorizedPersonDocumentNumber;
    @Schema(description = "Authorization start")
    private LocalDate authorizationStart;
    @Schema(description = "Authorization end")
    private LocalDate authorizationEnd;
    @Schema(description = "Authorized by")
    private String authorizedBy;
}