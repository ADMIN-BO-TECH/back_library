package co.com.botech.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MaintenanceOrderPriority {
    URGENTE("Urgente"),
    NORMAL("Normal");

    private final String label;
}
