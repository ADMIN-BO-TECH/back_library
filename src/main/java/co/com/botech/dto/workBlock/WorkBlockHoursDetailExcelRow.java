package co.com.botech.dto.workBlock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockHoursDetailExcelRow {
    @JsonProperty("Tipo fila")
    private String rowType; // BLOQUE | TOTAL DÍA
    @JsonProperty("Fecha")
    private String date; // yyyy-MM-dd
    @JsonProperty("Día")
    private String dayName; // lunes, martes...
    @JsonProperty("N° bloque")
    private Integer blockNumber; // posicion del bloque dentro del dia
    @JsonProperty("Inicio")
    private String startTime; // HH:mm
    @JsonProperty("Fin")
    private String endTime; // HH:mm, vacio si esta abierto
    @JsonProperty("Origen")
    private String source; // Asistencia | Manual
    @JsonProperty("Estado")
    private String state; // Completo | Incompleto
    @JsonProperty("Duración (hh:mm)")
    private String duration;
    @JsonProperty("Acumulado del día")
    private String accumulatedOfDay; // muestra en que bloque se pasa de 8 h
    @JsonProperty("Ordinarias diurnas")
    private String ordinaryDayHours;
    @JsonProperty("Ordinarias nocturnas (recargo nocturno)")
    private String ordinaryNightHours;
    @JsonProperty("Extra diurnas")
    private String extraDayHours;
    @JsonProperty("Extra nocturnas")
    private String extraNightHours;
}
