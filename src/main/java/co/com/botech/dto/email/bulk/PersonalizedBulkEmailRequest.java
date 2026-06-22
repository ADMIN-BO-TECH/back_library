package co.com.botech.dto.email.bulk;

import co.com.botech.constants.EmailTemplateAssignation;
import co.com.botech.dto.email.EmailVariables;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalizedBulkEmailRequest<T extends EmailVariables> {

    @NotEmpty
    private List<PersonalizedRecipient<T>> recipients;

    @NotBlank
    private String subject;

    @NotNull
    private EmailTemplateAssignation template;

    private Integer chunkSize;
}