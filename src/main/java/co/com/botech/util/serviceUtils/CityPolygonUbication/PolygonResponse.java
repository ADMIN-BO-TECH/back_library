package co.com.botech.util.serviceUtils.CityPolygonUbication;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PolygonResponse {
	String ubication;
	Boolean isCity;
}
