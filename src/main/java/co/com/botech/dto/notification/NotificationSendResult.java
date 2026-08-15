package co.com.botech.dto.notification;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSendResult {

    private int totalRequested;

    private int sent;

    private int failed;

    private int skippedByPreference;

    private int invalidatedTokens;

    @Builder.Default
    private List<Long> sentUserIds = new ArrayList<>();

    @Builder.Default
    private List<FailedPushDelivery> failures = new ArrayList<>();
}
