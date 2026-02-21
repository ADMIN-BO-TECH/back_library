package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.Employee;
import co.com.botech.repository.EmployeeRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeUtils {

    private EmployeeRepository employeeRepository;

    public Employee getEmployeeByDocumentNumberSocket(String documentNumber, String details, String code) {
        String detailsValue = (details == null || details.isBlank()) ? "" : details;
        return employeeRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        code + ". No se han encontrado empleados con el número de documento " + documentNumber +
                                (detailsValue.isEmpty() ? "" : " - " + detailsValue)));
    }
}
