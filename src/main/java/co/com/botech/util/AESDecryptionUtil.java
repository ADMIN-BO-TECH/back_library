package co.com.botech.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESDecryptionUtil {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String INIT_VECTOR = "cJx7TuPZL1qKD9sA";

    public static String decrypt(String encryptedText) {
        try {
            String key = getSecretKey();
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException("Error al desencriptar el documento", ex);
        }
    }

    private static String getSecretKey() {
        try (InputStream inputStream = AESDecryptionUtil.class.getClassLoader()
                .getResourceAsStream("clave_aes.txt")) {

            if (inputStream == null) {
                throw new RuntimeException("No se encontró el archivo clave_aes.txt en el classpath.");
            }

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim();

        } catch (IOException e) {
            throw new RuntimeException("Error al leer clave_aes.txt", e);
        }
    }
}
