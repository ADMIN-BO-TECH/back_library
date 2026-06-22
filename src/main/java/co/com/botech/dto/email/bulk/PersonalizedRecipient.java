package co.com.botech.dto.email.bulk;

import co.com.botech.dto.email.EmailVariables;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalizedRecipient<T extends EmailVariables> {

    @Email
    @NotBlank
    private String email;

    @NotNull
    private T variables;
}