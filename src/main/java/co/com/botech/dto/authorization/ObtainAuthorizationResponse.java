package co.com.botech.dto.authorization;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObtainAuthorizationResponse {
    private Long idAuthorization;
    private Long idStudent;
    private String studentName;
    private String studentGrade;
    private String authorizedPersonName;
    private LocalDate authorizationStart;
    private LocalDate authorizationEnd;
    private String authorizedBy;
}
