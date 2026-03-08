package co.com.botech.dto.student;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentSummaryResponse {
    private Long studentSchoolId;
    private List<SummarySectionResponse> sections;
}
