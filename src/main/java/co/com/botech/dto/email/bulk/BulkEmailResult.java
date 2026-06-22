package co.com.botech.dto.email.bulk;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BulkEmailResult {
    private int totalRequested;
    private int sent;
    private int failed;
    private List<String> sentTo;
    private List<FailedDelivery> failures;
}
