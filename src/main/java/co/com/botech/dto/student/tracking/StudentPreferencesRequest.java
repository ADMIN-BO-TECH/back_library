package co.com.botech.dto.student.tracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentPreferencesRequest {
    private Long studentRecordId;
    private Boolean leaveAlone;
    private Boolean pickUpAlone;
}