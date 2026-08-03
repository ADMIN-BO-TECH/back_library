package co.com.botech.dto.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiErrorResponse {
    @Builder.Default
    private boolean success = false;
    private String code;
    private String message;
}
