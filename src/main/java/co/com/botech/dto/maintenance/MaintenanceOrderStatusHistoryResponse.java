package co.com.botech.dto.maintenance;

import co.com.botech.constants.MaintenanceOrderStatus;
import co.com.botech.constants.ProfileKindSSO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "Registro del historial de estados de una orden de mantenimiento")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class MaintenanceOrderStatusHistoryResponse {
    @Schema(description = "ID del registro")
    private Long id;
    @Schema(description = "Estado anterior; null en el registro de creación")
    private MaintenanceOrderStatus fromStatus;
    @Schema(description = "Estado resultante")
    private MaintenanceOrderStatus toStatus;
    @Schema(description = "ID del usuario que hizo el cambio; null si lo hizo el sistema")
    private Long changedByUserId;
    @Schema(description = "Nombre de quien hizo el cambio; null si no se encuentra en users o es el sistema")
    private String changedByName;
    @Schema(description = "Perfil con el que se hizo el cambio; SISTEMA si lo hizo el propio sistema")
    private ProfileKindSSO changedByRole;
    @Schema(description = "Fecha y hora del cambio")
    private LocalDateTime changedAt;
    @Schema(description = "Motivo o nota")
    private String comment;
}
