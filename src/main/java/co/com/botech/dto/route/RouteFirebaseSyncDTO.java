package co.com.botech.dto.route;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RouteFirebaseSyncDTO {
    private Long routeId;
    private String routeName;
    private String routeDays;
    private String startTime;
    private String endTime;
    private Boolean status;
    private String routeType;
    private String schoolName;

    private Long vehicleId;
    private String plateNumber;
    private String fleetNumber;

    private Long assistantId;
    private String assistantFirstName;
    private String assistantDocumentNumber;
    private String assistantEmail;
    private String assistantPosition;
    private String assistantRfidTag;

    private Long operatorId;
    private String operatorFirstName;
    private String operatorDocumentNumber;
    private String operatorEmail;
    private String operatorPosition;
    private String operatorRfidTag;

    private Long rfidRegisterId;
}