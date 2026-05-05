package co.com.botech.constants;

import lombok.Getter;

import java.util.Set;

@Getter
public class StorageConstants {
    public static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5MB
    public static final Set<String> ALLOWED_IMAGE_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );
    public static final String BUCKET_NAME = "tehc-app.appspot.com";
}
