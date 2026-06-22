package co.com.botech.dto.email.SchoolEmployee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailServiceAuthRequest {
    private String username;
    private String password;
}
