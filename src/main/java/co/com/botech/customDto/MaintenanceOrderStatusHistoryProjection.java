package co.com.botech.customDto;

import java.time.LocalDateTime;

/** Fila del historial con el nombre de quien actuó, resuelto contra users en la misma consulta. */
public interface MaintenanceOrderStatusHistoryProjection {
    Long getId();
    String getFromStatus();
    String getToStatus();
    Long getChangedByUserId();
    String getChangedByRole();
    LocalDateTime getChangedAt();
    String getComment();
    String getChangedByFirstName();
    String getChangedByLastName();
}
