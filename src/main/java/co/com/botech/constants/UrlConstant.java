package co.com.botech.constants;

import lombok.Getter;

@Getter
public enum UrlConstant {

    NOTIFICACIONES_API("http://botech.com.co/notification");

    private final String value;

    UrlConstant(String value) {
        this.value = value;
    }
}
