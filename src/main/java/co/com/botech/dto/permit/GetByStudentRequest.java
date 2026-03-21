package co.com.botech.dto.permit;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetByStudentRequest {
    private Long studentRecordId;
}
