package co.com.botech.dto.attendance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceExcelObject implements AttendanceResponseProjection {

    @JsonProperty("Id de Registro")
    private Long attendanceRegisterId;
    @JsonProperty("Fecha y Hora")
    private LocalDateTime attendanceDateTime;
    @JsonProperty("Código de Colegio/Documento")
    private String schoolIdentifier;
    @JsonProperty("Tipo de Usuario")
    private String typeUser;
    @JsonProperty("Nombre")
    private String userName;
    @JsonProperty("Curso/Categoría")
    private String category;
    @JsonProperty("Nombre de Sistema")
    private String systemName;
    @JsonProperty("Tipo de Registro")
    private String registerType;
    @JsonProperty("Tag de Lector")
    private String rfidTag;
    @JsonProperty("Descripción de Lector")
    private String rfidDescription;
}
