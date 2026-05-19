package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.User;
import co.com.botech.repository.UserRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class UserUtils {
    private final UserRepository userRepository;

    public List<String> getUserTokens(List<User> userList) {
        return userList.stream().map(
                User::getFcmToken
        ).toList();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(
                        CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el usuario con ID " + userId
                ));
    }

    public String resolveAuthorName(String firebaseUid) {
        if (firebaseUid == null || firebaseUid.isEmpty()) {
            return "Usuario desconocido";
        }

        try {
            return userRepository.findByFirebaseUid(firebaseUid)
                    .map(user -> user.getFirstName() + " " + user.getLastName())
                    .orElse("Usuario desconocido");

        } catch (Exception e) {
            log.warn("No se pudo resolver el nombre para UID: {}. Error: {}", firebaseUid, e.getMessage());
            return "Usuario desconocido";
        }
    }
}
