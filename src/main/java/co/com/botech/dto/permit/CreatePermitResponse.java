package co.com.botech.dto.permit;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePermitResponse {
    private String message;
    private String email;
    private Boolean approved;
}