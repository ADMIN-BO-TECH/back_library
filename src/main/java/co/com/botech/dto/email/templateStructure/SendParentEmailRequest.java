package co.com.botech.dto.email.templateStructure;

import co.com.botech.dto.email.EmailVariables;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendParentEmailRequest implements EmailVariables {
    private String familyCode;
    private List<String> studentNames;
    private String schoolName;
}
