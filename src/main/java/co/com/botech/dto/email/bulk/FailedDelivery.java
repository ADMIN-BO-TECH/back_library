package co.com.botech.dto.email.bulk;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FailedDelivery {
    private String email;
    private String reason;
}
