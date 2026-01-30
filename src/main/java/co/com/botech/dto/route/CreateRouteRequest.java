package co.com.botech.dto.route;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRouteRequest {

    private Long routeId;

    @NotNull(message = "The vehicleId cannot be null")
    @Min(value = 1, message = "The vehicleId must be a positive number")
    private Long vehicleId;

    @NotNull(message = "The routeDays cannot be null")
    @Pattern(regexp = "^[L,M,X,J,V,S,Dl,m,x,j,v,s,d, ]+$",
            message = "The routeDays can only contain 'L, M, X, J, V, S, D' separated by commas")
    private String routeDays;

    @NotNull(message = "The routeName cannot be null")
    @Size(min = 3, max = 50, message = "The routeName must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0-9 -]+$",
            message = "The routeName can only contain letters, numbers and hyphens")
    private String routeName;

    @NotNull(message = "The operatorId cannot be null")
    @Min(value = 1, message = "The operatorId must be a positive integer")
    private Long operatorId;

    @NotNull(message = "The assistantId cannot be null")
    @Min(value = 1, message = "The assistantId must be a positive integer")
    private Long assistantId;

    @NotNull(message = "The startTime cannot be null")
    @Pattern(regexp = "^(0?[1-9]|1[0-2]):[0-5]\\d [APap][Mm]$",
            message = "The startTime must be in format hh:mm AM/PM (e.g. 3:20 PM)")
    private String startTime;

    @NotNull(message = "The endTime cannot be null")
    @Pattern(regexp = "^(0?[1-9]|1[0-2]):[0-5]\\d [APap][Mm]$",
            message = "The endTime must be in format hh:mm AM/PM (e.g. 3:20 PM)")
    private String endTime;

    @Size(min = 3, max = 50, message = "The routeType must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÜÑa-záéíóúüñ -]+$",
            message = "The routeType can only contain letters")
    private String routeType;

    private boolean overlapConflicts;
}