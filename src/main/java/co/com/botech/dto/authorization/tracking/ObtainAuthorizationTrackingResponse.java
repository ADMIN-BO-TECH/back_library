package co.com.botech.dto.authorization.tracking;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObtainAuthorizationTrackingResponse {
    private Long idAuthorization;
    private Long studentRecordId;
    private Long studentId;
    private String studentName;
    private String studentGrade;
    private String authorizedPersonName;
    private String authorizedPersonDocumentType;
    private String authorizedPersonDocumentNumber;
    private LocalDate authorizationStart;
    private LocalDate authorizationEnd;
    private String authorizedBy;
}