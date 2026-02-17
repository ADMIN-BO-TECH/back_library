package co.com.botech.dto.employeeAttendance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkedHoursDetailExcelRow {

    @JsonProperty("Tipo fila")
    private String rowType; // REGISTRO | TOTAL_DIA

    @JsonProperty("Fecha")
    private String date; // yyyy-MM-dd

    @JsonProperty("Hora")
    private String time; // HH:mm:ss

    @JsonProperty("Dirección")
    private String address;

    @JsonProperty("Latitud")
    private Double latitude;

    @JsonProperty("Longitud")
    private Double longitude;

    @JsonProperty("Horas del día (hh:mm)")
    private String workedHoursForDay;
}
