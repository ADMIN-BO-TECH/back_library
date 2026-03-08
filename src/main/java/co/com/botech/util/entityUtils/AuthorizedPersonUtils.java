package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.AuthorizedPerson;
import co.com.botech.entity.Student;
import co.com.botech.repository.AuthorizedPersonRepository;
import co.com.botech.repository.StudentRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthorizedPersonUtils {
    private final AuthorizedPersonRepository authorizedPersonRepository;

    public AuthorizedPerson getAuthorizedPersonById(Long authorizedPersonId) {
        return authorizedPersonRepository.findById(authorizedPersonId)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado la persona autorizada con el id " + authorizedPersonId));
    }
}
