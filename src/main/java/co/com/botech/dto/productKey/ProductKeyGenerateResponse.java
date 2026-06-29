package co.com.botech.dto.productKey;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductKeyGenerateResponse {
    private String productKey;
    private long exp;
}
