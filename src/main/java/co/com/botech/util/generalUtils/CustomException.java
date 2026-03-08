package co.com.botech.util.generalUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;

public class CustomException extends RuntimeException {
    private final CustomExceptionCodeConstants code;
    private final String detail;

    public CustomException(CustomExceptionCodeConstants code, String message) {
        super(message);
        this.code = code;
        this.detail = message;
    }

    public CustomExceptionCodeConstants getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }
}
