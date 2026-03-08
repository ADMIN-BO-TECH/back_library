package co.com.botech.dto.schoolEmployee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolEmployeeResponse {

    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String position;
}