package co.com.botech.dto.email.SchoolEmployee;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailServiceEmployeeRequest {
    private String codigoFamilia;
    private String clientName;
    private List<String> emails;
}
