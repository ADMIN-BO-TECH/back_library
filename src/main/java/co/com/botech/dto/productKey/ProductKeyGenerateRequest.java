package co.com.botech.dto.productKey;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductKeyGenerateRequest {
    private String schoolCode;
    private String profileKindSlug;
    private String familyCode;
    private long ttlSeconds;
}
