package co.com.botech.dto.attendance;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StudentCallResponse {
    private boolean success;
    private Integer parada;
    private List<StudentCallItem> students;
}
