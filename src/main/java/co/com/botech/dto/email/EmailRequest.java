package co.com.botech.dto.email;

import co.com.botech.constants.EmailTemplateAssignation;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest<T extends EmailVariables> {

    private List<String> to;
    private String subject;
    private EmailTemplateAssignation template;
    private T variables;

}