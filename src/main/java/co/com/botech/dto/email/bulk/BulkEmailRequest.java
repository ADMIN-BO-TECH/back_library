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
public class BulkEmailRequest<T extends EmailVariables> {

    @NotEmpty
    private List<String> recipients;

    @NotBlank
    private String subject;

    @NotNull
    private EmailTemplateAssignation template;

    @NotNull
    private T variables;

    private Integer chunkSize;
}
