package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.customDto.VehicleCompleteResponse;
import co.com.botech.entity.Vehicle;
import co.com.botech.entity.VehicleDetail;
import co.com.botech.repository.VehicleDetailRepository;
import co.com.botech.repository.VehicleRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleUtils {

    private final VehicleRepository vehicleRepository;
    private final VehicleDetailRepository vehicleDetailRepository;

    public VehicleCompleteResponse getVehicleCompleteResponse(Long vehicleId) {
        return vehicleRepository.findVehiclesComplete(vehicleId)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el vehículo con ID " + vehicleId));
    }

    public Pair<Vehicle, VehicleDetail> getVehicleObjects(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new CustomException(
                        CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el vehículo con ID " + vehicleId
                ));
        VehicleDetail vehicleDetail = vehicleDetailRepository.findByVehicleId(vehicleId)
                .orElseThrow(() -> new CustomException(
                        CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el detalle del vehículo con ID " + vehicleId
                ));
        return Pair.of(vehicle, vehicleDetail);
    }

    public VehicleCompleteResponse getVehicleCompleteResponseByFleetNumber(String fleetNumber) {
        return vehicleRepository.findVehiclesCompleteByFleetNumber(fleetNumber)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el vehículo con número de flota: " + fleetNumber));
    }
}