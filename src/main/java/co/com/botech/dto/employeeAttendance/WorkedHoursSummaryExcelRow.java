package co.com.botech.dto.employeeAttendance;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkedHoursSummaryExcelRow {

    @JsonProperty("Nombre")
    private String firstName;

    @JsonProperty("Documento")
    private String documentNumber;

    @JsonProperty("Horas trabajadas (hh:mm)")
    private String totalWorkedHours;

    @JsonProperty("Días con cálculo")
    private Integer daysWithCalculation;

    @JsonProperty("Días incompletos")
    private Integer daysIncomplete;
}
