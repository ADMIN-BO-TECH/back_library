package co.com.botech.dto.notification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FailedPushDelivery {

    private Long userId;

    private String token;

    private String errorCode;

    private String message;

    private boolean tokenInvalidated;
}
