package co.com.botech.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateFcmTokenRequest(

        @NotBlank(message = "firebaseUid es obligatorio")
        String firebaseUid,

        @NotBlank(message = "fcmToken es obligatorio")
        String fcmToken
) {}
