package co.com.botech.dto.email.SchoolEmployee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailServiceAuthResponse {
    private CodeAndMessageResponse codeAndMessageResponse;
    private String jwt;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CodeAndMessageResponse {
        private String code;
        private String message;
    }
}
