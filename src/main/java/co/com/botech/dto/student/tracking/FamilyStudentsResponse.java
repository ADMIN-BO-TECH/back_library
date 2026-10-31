package co.com.botech.dto.student.tracking;


import co.com.botech.dto.student.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Students of a family")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyStudentsResponse {
    @Schema(description = "Family students")
    private List<StudentDTO> students;
}