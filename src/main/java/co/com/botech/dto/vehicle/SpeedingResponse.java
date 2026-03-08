package co.com.botech.dto.vehicle;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpeedingResponse {
	String dateTime;
	String speed;
	String latitude;
	String longitude;
	String zone;
}
