package co.com.botech.dto.workBlock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockHoursSummaryExcelRow {
    @JsonProperty("Nombre")
    private String firstName;
    @JsonProperty("Documento")
    private String documentNumber;
    @JsonProperty("Cargo")
    private String position;
    @JsonProperty("Días trabajados")
    private Integer daysWorked;
    @JsonProperty("Bloques completos")
    private Integer completeBlocks;
    @JsonProperty("Bloques incompletos")
    private Integer incompleteBlocks;
    @JsonProperty("Total (hh:mm)")
    private String totalHours;
    @JsonProperty("Ordinarias diurnas")
    private String ordinaryDayHours;
    @JsonProperty("Ordinarias nocturnas (recargo nocturno)")
    private String ordinaryNightHours;
    @JsonProperty("Extra diurnas")
    private String extraDayHours;
    @JsonProperty("Extra nocturnas")
    private String extraNightHours;
}
