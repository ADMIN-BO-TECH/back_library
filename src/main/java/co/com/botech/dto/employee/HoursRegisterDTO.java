package co.com.botech.dto.employee;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoursRegisterDTO {
    private double dayHours;
    private double nightHours;
    private double totalHours;
}
