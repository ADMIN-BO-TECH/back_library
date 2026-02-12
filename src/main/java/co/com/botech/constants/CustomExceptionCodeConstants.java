package co.com.botech.constants;

public enum CustomExceptionCodeConstants {
    BAD_REQUEST(400),
    ENTITY_NOT_FOUND(404),
    CONFLICT(409),
    INVALID_REQUEST(405);

    private final int code;

    CustomExceptionCodeConstants(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
