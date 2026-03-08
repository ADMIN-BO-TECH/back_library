package co.com.botech.dto.stop;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PersonCrudResponse {
    private Long personRecordId;
    private String personName;
}
