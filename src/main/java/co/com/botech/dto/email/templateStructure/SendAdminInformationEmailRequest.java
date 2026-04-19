package co.com.botech.dto.email.templateStructure;

import co.com.botech.dto.email.EmailVariables;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendAdminInformationEmailRequest implements EmailVariables {
    private String name;
    private String school;
    private Long phoneNumber;
    private String message;
    private String system;
}

