package co.com.botech.util.entityUtils;


import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.DocumentType;
import co.com.botech.repository.DocumentTypeRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentTypeUtils {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentType getDocumentType(String documentTypeName) {
        return documentTypeRepository.findByName(documentTypeName)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el documento de tipo " + documentTypeName));
    }

    public DocumentType getDocumentTypeById(Long id) {
        return documentTypeRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el documento de tipo con id " + id));
    }

    public void existsDocumentTypeById(Long id) {
        if (!documentTypeRepository.existsById(id)) {
            throw new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                    "No se ha encontrado el documento de tipo con id " + id);
        }
    }
}
