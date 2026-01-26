package co.com.botech.dto.permit;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermitResponse {
    private Long permitId;
    private Long studentSchoolId ;
    private LocalDateTime createdDate;
    private LocalDate permitDate;
    private String requestedBy;
    private String description;
    private String response;
    private String repliedBy;
    private String permitStatus;
    private String permitType;
}
