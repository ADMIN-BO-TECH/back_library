package co.com.botech.dto.email.emailRequest;

import co.com.botech.dto.email.EmailVariables;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentEmailRequest{
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El codigo de familia contiene caracteres inválidos")
    private String familyCode;
    @NotEmpty
    private List<@Email(message = "El formato del correo no es válido") String> emails;
}
