package co.com.botech.util.preoperation;

import co.com.botech.constants.VehicleOperationalStatus;
import co.com.botech.entity.Preoperation;
import co.com.botech.entity.PreoperationItem;

import java.util.List;

public record PreoperationEvaluationResult(
        Preoperation preoperation,
        List<PreoperationItem> items,
        VehicleOperationalStatus vehicleStatus
) {}
