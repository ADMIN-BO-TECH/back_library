package co.com.botech.dto.rfidRegister;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "RFID register")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RfidRegisterDTO {
    @Schema(description = "RFID register ID")
    private Long rfidRegisterId;
    @Schema(description = "Description")
    private String description;
    @Schema(description = "RFID tag")
    private String rfidTag;
    @Schema(description = "Kind device name")
    private String kindDeviceName;
    @Schema(description = "School name")
    private String schoolName;
}
