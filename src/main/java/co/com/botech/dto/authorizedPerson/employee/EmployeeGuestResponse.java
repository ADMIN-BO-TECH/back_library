package co.com.botech.dto.authorizedPerson.employee;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeGuestResponse {
    private Long guestAuthorizationId;
    private Long authorizedPersonId;
    private Long documentTypeId;
    private String documentTypeName;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String type;
    private String description;
    private String authorizedBy;
    private LocalDate authorizationStart;
    private LocalDate authorizationEnd;
    private Boolean isPersonDataEditable;
    private Boolean isCurrentlyAuthorized;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
