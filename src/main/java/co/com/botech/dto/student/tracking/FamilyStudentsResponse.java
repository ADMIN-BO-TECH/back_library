package co.com.botech.dto.student.tracking;


import co.com.botech.dto.student.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyStudentsResponse {
    private List<StudentDTO> students;
}