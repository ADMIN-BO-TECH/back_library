package co.com.botech.dto.attendance;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricAttendanceResponse {
    private Long totalRegisters;
    private int totalPages;
    private List<AttendanceResponse> registers;
}
