package co.com.botech.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "User device push token (FCM) update")
public record UpdateFcmTokenRequest(

        @Schema(description = "User's Firebase UID", example = "Xy12AbCdEf34GhIj56KlMn")
        @NotBlank(message = "firebaseUid es obligatorio")
        String firebaseUid,

        @Schema(description = "Device Firebase Cloud Messaging token", example = "fcm-token-de-ejemplo")
        @NotBlank(message = "fcmToken es obligatorio")
        String fcmToken
) {}
