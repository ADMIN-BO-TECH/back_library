package co.com.botech.dto.authorization;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorizationDTO {
    private Long authorizationId;
    private Long studentSchoolId;
    private String authorizedPersonDocument;
    private LocalDate creationDate;
    private LocalDate initDate;
    private LocalDate endDate;
    private String authorizedBy;
}
