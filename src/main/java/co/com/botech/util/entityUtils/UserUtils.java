package co.com.botech.util.entityUtils;

import co.com.botech.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class UserUtils {
    public List<String> getUserTokens(List<User> userList) {
        return userList.stream().map(
                User::getFcmToken
        ).toList();
    }
}
