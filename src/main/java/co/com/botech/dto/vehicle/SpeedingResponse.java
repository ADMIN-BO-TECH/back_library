package co.com.botech.dto.vehicle;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

@Schema(description = "Speeding event")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpeedingResponse {
	@Schema(description = "Event date and time")
	String dateTime;
	@Schema(description = "Speed")
	String speed;
	@Schema(description = "Latitude")
	String latitude;
	@Schema(description = "Longitude")
	String longitude;
	@Schema(description = "Zone")
	String zone;
}
