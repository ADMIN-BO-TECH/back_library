package co.com.botech.dto.authorizedPerson.employee;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Guest authorized by a school employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeGuestResponse {
    @Schema(description = "Guest authorization ID", example = "57")
    private Long guestAuthorizationId;
    @Schema(description = "Authorized person ID", example = "88")
    private Long authorizedPersonId;
    @Schema(description = "Document type ID", example = "1")
    private Long documentTypeId;
    @Schema(description = "Document type name", example = "Cédula de ciudadanía")
    private String documentTypeName;
    @Schema(description = "Document number", example = "1020304050")
    private String documentNumber;
    @Schema(description = "Guest's first names", example = "Carlos")
    private String firstName;
    @Schema(description = "Guest's last names", example = "Rodríguez")
    private String lastName;
    @Schema(description = "Authorized person type", example = "Invitado")
    private String type;
    @Schema(description = "Visit reason", example = "Reunión con coordinación")
    private String description;
    @Schema(description = "Employee who authorized the guest", example = "Laura Gómez")
    private String authorizedBy;
    @Schema(description = "Authorization start date", example = "2026-10-20")
    private LocalDate authorizationStart;
    @Schema(description = "Authorization end date", example = "2026-10-20")
    private LocalDate authorizationEnd;
    @Schema(description = "Whether the employee can edit personal data", example = "true")
    private Boolean isPersonDataEditable;
    @Schema(description = "Whether the authorization is valid today", example = "true")
    private Boolean isCurrentlyAuthorized;
    @Schema(description = "Created date and time", example = "2026-10-20T07:30:00")
    private LocalDateTime createdAt;
    @Schema(description = "Last updated date and time", example = "2026-10-21T09:15:00")
    private LocalDateTime updatedAt;
}
