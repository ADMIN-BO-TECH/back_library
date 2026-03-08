package co.com.botech.dto.rfidRegister;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RfidRegisterDTO {
    private Long rfidRegisterId;
    private String description;
    private String rfidTag;
    private String kindDeviceName;
    private String schoolName;
}
